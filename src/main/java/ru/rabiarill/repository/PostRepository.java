package ru.rabiarill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rabiarill.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
