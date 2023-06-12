package ru.rabiarill.service.image.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rabiarill.model.Image;
import ru.rabiarill.model.Post;
import ru.rabiarill.repository.ImageRepository;
import ru.rabiarill.service.image.ImageService;

import java.util.List;

@Service("imageService")
@Transactional(readOnly = true)
public class ImageServiceImpl implements ImageService {

   private final ImageRepository imageRepository;

   @Autowired
   public ImageServiceImpl(ImageRepository imageRepository) {
      this.imageRepository = imageRepository;
   }

   /**
    * @see ImageService#save(List, Post)
    */
   @Override
   @Transactional
   public List<Image> save(List<Image> images, Post post) {
      images.forEach(p -> p.setPost(post));
      return imageRepository.saveAll(images);
   }

   /**
    * @see ImageService#delete(List)
    */
   @Override
   @Transactional
   public void delete(List<Image> images) {
      imageRepository.deleteAll(images);
   }
}
