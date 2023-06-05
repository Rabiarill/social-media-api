package ru.rabiarill.service.post.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rabiarill.exception.model.post.PostNotFoundException;
import ru.rabiarill.model.Post;
import ru.rabiarill.repository.PostRepository;
import ru.rabiarill.service.post.PostService;

import java.util.List;

@Service("postService")
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

   private final PostRepository postRepository;

   @Autowired
   public PostServiceImpl(PostRepository postRepository) {
      this.postRepository = postRepository;
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
}
