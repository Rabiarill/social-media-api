package ru.rabiarill.service.post.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rabiarill.dto.model.PostDTO;
import ru.rabiarill.exception.model.post.PostNotFoundException;
import ru.rabiarill.model.Post;
import ru.rabiarill.repository.PostRepository;
import ru.rabiarill.service.post.PostService;

import java.util.List;
import java.util.stream.Collectors;

@Service("postService")
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

   private final PostRepository postRepository;
   private final ModelMapper modelMapper;

   @Autowired
   public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
      this.postRepository = postRepository;
      this.modelMapper = modelMapper;
   }

   /**
    * @see PostService#findById(int)
    */
   @Override
   public Post findById(int id) {
      return postRepository
              .findById(id)
              .orElseThrow(() -> new PostNotFoundException("Post with id = " + id + " not found"));
   }

   /**
    * @see PostService#findByOwnerId(int)
    */
   @Override
   public List<Post> findByOwnerId(int id) {
      return postRepository.findByOwnerId(id);
   }

   /**
    * @see PostService#findByOwnerId(int, int, int)
    */
   @Override
   public List<Post> findByOwnerId(int id, int page, int itemPerPage){
      return postRepository.findByOwnerId(id, PageRequest.of(page, itemPerPage));
   }

   /**
    * @see PostService#save(Post)
    */
   @Override
   @Transactional
   public Post save(Post post) {
      return postRepository.save(post);
   }

   /**
    * @see PostService#deleteById(int)
    */
   @Override
   @Transactional
   public void deleteById(int id) {
      postRepository.deleteById(id);
   }

   /**
    * @see PostService#convertToPost(PostDTO)
    */
   @Override
   public Post convertToPost(PostDTO postDTO) {
      return modelMapper.map(postDTO, Post.class);
   }

   /**
    * @see PostService#convertToPost(List)
    */
   @Override
   public List<Post> convertToPost(List<PostDTO> postDTOs) {
      return postDTOs
              .stream()
              .map(this::convertToPost)
              .collect(Collectors.toList());
   }

   /**
    * @see PostService#convertToDTO(Post)
    */
   @Override
   public PostDTO convertToDTO(Post post) {
      return modelMapper.map(post,PostDTO.class);
   }

   /**
    * @see PostService#convertToDTO(List)
    */
   @Override
   public List<PostDTO> convertToDTO(List<Post> posts) {
      return posts
              .stream()
              .map(this::convertToDTO)
              .collect(Collectors.toList());
   }

}
