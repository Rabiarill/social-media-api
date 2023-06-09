package ru.rabiarill.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rabiarill.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
   List<Post> findByOwnerId(int id);
   List<Post> findByOwnerId(int id, Pageable pageable);
}
