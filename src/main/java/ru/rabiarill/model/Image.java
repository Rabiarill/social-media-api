package ru.rabiarill.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PostImages")
public class Image {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private int id;

   @ManyToOne
   @JoinColumn(name = "post_id", referencedColumnName = "id")
   private Post post;

   @Column(name = "image_url")
   private String imageUrl;

   public Image() { }

   public Image(Post post, String imageUrl) {
      this.post = post;
      this.imageUrl = imageUrl;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public Post getPost() {
      return post;
   }

   public void setPost(Post post) {
      this.post = post;
   }

   public String getImageUrl() {
      return imageUrl;
   }

   public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Image image = (Image) o;
      return id == image.id &&
              post.equals(image.post) &&
              imageUrl.equals(image.imageUrl);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, post, imageUrl);
   }
}
