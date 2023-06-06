package ru.rabiarill.dto.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

public class PostDTO {

   private int id;

   @NotEmpty(message = "Title should not be empty")
   private String title;

   private String content;

   @Temporal(TemporalType.TIMESTAMP)
   @DateTimeFormat(pattern = "dd/MM/YYYY hh/mm")
   private Date createdAt;

   @Temporal(TemporalType.TIMESTAMP)
   @DateTimeFormat(pattern = "dd/MM/YYYY hh/mm")
   private Date updated_at;

   private List<ImageDTO> images;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public Date getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(Date createdAt) {
      this.createdAt = createdAt;
   }

   public Date getUpdated_at() {
      return updated_at;
   }

   public void setUpdated_at(Date updated_at) {
      this.updated_at = updated_at;
   }

   public List<ImageDTO> getImages() {
      return images;
   }

   public void setImages(List<ImageDTO> images) {
      this.images = images;
   }


}
