package ru.rabiarill.service.post;

import ru.rabiarill.dto.model.PostDTO;
import ru.rabiarill.exception.model.post.PostNotFoundException;
import ru.rabiarill.model.Post;

import java.util.List;

public interface PostService {

   /**
    * Method that find post by id.
    *
    * @param id
    * @return <code>Post</code> object
    *
    * @throws PostNotFoundException
    */
   Post findById(int id);

   /**
    * Method that find post by owner id.
    *
    * @param id
    * @return <code>List<Post></code> object
    */
   List<Post> findByOwnerId(int id);

   /**
    * Method that find all the posts of the people the user is subscribed.
    *
    * @param id
    * @return <code>List<Post></code> object
    */
   List<Post> findBySubscriberId(int id);

   /**
    * Method  find all posts by owner id with pagination.
    *
    * @param id
    * @param page
    * @param itemsPerPage
    * @return <code>List<Post></code> object
    */
   List<Post> findByOwnerId(int id, int page, int itemsPerPage);

   /**
    * Method that save an object Post.
    *
    * @param post
    * @return <code>Post</code> object
    */
   Post save(Post post);

   /**
    * Method that remove an object Post by an id.
    *
    * @param id
    */
   void deleteById(int id);

   /**
    * Method that convert PostDTO object to Post object.
    *
    * @param postDTO
    * @return <code>Post</code> object
    */
   Post convertToPost(PostDTO postDTO);

   /**
    * Method than convert list of PostDTO objects to list of Post objects.
    *
    * @param postDTOs
    * @return <code>List<Post></code> object
    */
   List<Post> convertToPost(List<PostDTO> postDTOs);

   /**
    * Method that convert Post object to PostDTO object.
    *
    * @param post
    * @return <code>PostDTO</code> object
    */
   PostDTO convertToDTO(Post post);

   /**
    * Method than convert list of Post objects to list of PostDTO objects.
    *
    * @param posts
    * @return <code>List<PostDTO></code> object
    */
   List<PostDTO> convertToDTO(List<Post> posts);

}
