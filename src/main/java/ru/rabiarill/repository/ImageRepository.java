package ru.rabiarill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rabiarill.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

}
