package ru.rabiarill.service.image;

import ru.rabiarill.model.Image;
import ru.rabiarill.model.Post;

import java.util.List;

public interface ImageService {

   /**
    * Method save images in database.
    *
    * @param images
    * @return <code>List<Image></code> object
    */
   List<Image> save(List<Image> images, Post post);

   /**
    * Method delete images in database.
    *
    * @param images
    */
   void delete(List<Image> images);
}
