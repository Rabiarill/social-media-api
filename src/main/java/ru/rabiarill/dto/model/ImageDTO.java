package ru.rabiarill.dto.model;

import org.modelmapper.ModelMapper;
import ru.rabiarill.model.Image;


public class ImageDTO {
   private int id;

   private String imageUrl;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getImageUrl() {
      return imageUrl;
   }

   public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
   }

   public Image convertToImage(){
      return new ModelMapper().map(this, Image.class);
   }
}
