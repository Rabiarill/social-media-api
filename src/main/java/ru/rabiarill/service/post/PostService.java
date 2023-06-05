package ru.rabiarill.service.post;

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
    * @return @return <code>List<Post></code> object
    */
   List<Post> findByOwnerId(int id);

   /**
    * Method that save an object Post.
    *
    * @param post
    * @return @return <code>Post</code> object
    */
   Post save(Post post);

   /**
    * Method that remove an object Post by an id.
    *
    * @param id
    */
   void deleteById(int id);

}
