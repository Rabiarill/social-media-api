package ru.rabiarill.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rabiarill.dto.model.PostDTO;
import ru.rabiarill.exception.model.NoAccessException;
import ru.rabiarill.exception.model.post.NotValidPostException;
import ru.rabiarill.model.Post;
import ru.rabiarill.model.User;
import ru.rabiarill.service.post.PostService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/social-media/v1/post")
public class PostController {

   private final PostService postService;

   @Autowired
   public PostController(PostService postService) {
      this.postService = postService;
   }

   /**
    * Method that find all user posts.
    *
    * @param owner
    * @return <code>ResponseEntity</code> with a <code><List<PostDTO>><code> object and the HTTP status
    */
   @GetMapping
   public ResponseEntity<List<PostDTO>> getUserPosts(@AuthenticationPrincipal(expression = "user") User owner){
      List<PostDTO> response = Post.convertListToDTO(postService.findByOwnerId(owner.getId()));

      return new ResponseEntity<>(response, HttpStatus.OK);
   }

   /**
    * Method find all user posts with pagination.
    *
    * @param owner
    * @param page defaultValue = 0
    * @param itemsPerPage defaultValue = 5
    * @return <code>ResponseEntity</code> with a <code><List<PostDTO>><code> object and the HTTP status
    */
   @GetMapping("/pagination")
   public ResponseEntity<List<PostDTO>> getUserPostsWithPagination(@AuthenticationPrincipal(expression = "user") User owner,
                                                                   @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                   @RequestParam(value = "itemsPerPage", required = false, defaultValue = "5") int itemsPerPage){
      List<PostDTO> response = Post.convertListToDTO(postService.findByOwnerId(owner.getId(), page, itemsPerPage));

      return new ResponseEntity<>(response, HttpStatus.OK);
   }

   /**
    * Method create post in the database.
    *
    * @param postDTO
    * @param bindingResult
    * @param owner
    * @return <code>ResponseEntity</code> with a <code>PostDTO<code> object and the HTTP status
    */
   @PostMapping()
   public ResponseEntity<PostDTO> create(@RequestBody @Valid PostDTO postDTO,
                                            BindingResult bindingResult,
                                            @AuthenticationPrincipal(expression = "user") User owner) {
      if (bindingResult.hasErrors())
         throw new NotValidPostException(bindingResult.getFieldErrors());

      if (postDTO.getId() != 0)
         throw new NoAccessException("Post id must not be declared or should be 0");

      Post postToSave = postService.convertToPost(postDTO);
      postToSave.setOwner(owner);
      postToSave.setCreatedAt(new Date());
      postToSave.setUpdated_at(new Date());

      postService.save(postToSave);

      return new ResponseEntity<>(postService.convertToDTO(postToSave), HttpStatus.CREATED);
   }


   /**
    * Method that update post by id.
    *
    * @param postDTO
    * @param bindingResult
    * @param id
    * @param owner
    * @return <code>ResponseEntity</code> with a <code>PostDTO<code> object and the HTTP status
    */
   @PutMapping("/{id}")
   public ResponseEntity<PostDTO> update(@RequestBody @Valid PostDTO postDTO,
                                            BindingResult bindingResult,
                                            @PathVariable("id") int id,
                                            @AuthenticationPrincipal(expression = "user") User owner) {
      if (bindingResult.hasErrors())
         throw new NotValidPostException(bindingResult.getFieldErrors());

      Post postDB = postService.findById(id);
      if (postDB.getOwner().getId() != owner.getId())
         throw new NoAccessException("You should be owner of post to update it");

      Post postToUpdate = postService.convertToPost(postDTO);
      postToUpdate.setOwner(owner);
      postToUpdate.setUpdated_at(new Date());

      PostDTO response = postService.convertToDTO(postService.save(postToUpdate));

      return new ResponseEntity<>(response, HttpStatus.OK);
   }

   /**
    * Method delete post by id.
    *
    * @param id
    * @param owner
    * @return <code>ResponseEntity</code> with the HTTP status
    */
   @DeleteMapping("/{id}")
   public ResponseEntity<HttpStatus> deleteById(@PathVariable(value = "id") int id,
                                                @AuthenticationPrincipal(expression = "user") User owner) {

      Post postDb = postService.findById(id);

      if (postDb.getOwner().getId() != owner.getId())
         throw new NoAccessException("You should be owner of post to delete it");

      postService.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }

}
