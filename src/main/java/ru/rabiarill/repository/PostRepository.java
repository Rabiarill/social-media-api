package ru.rabiarill.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.rabiarill.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
   List<Post> findByOwnerId(int id);
   List<Post> findByOwnerId(int id, Pageable pageable);
   @Query(nativeQuery = true,
           value = "select * from posts p " +
                   "where p.user_id in " +
                      "(select s.subscribed_to_id " +
                      "from subscriptions s " +
                      "where s.subscriber_id = :id)")
   List<Post> findBySubscriberId(int id);
}
