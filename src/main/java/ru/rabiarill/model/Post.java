package ru.rabiarill.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Posts")
public class Post {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private int id;

   @ManyToOne
   @JoinColumn(name = "user_id", referencedColumnName = "id")
   private User owner;

   @Column(name = "title")
   private String title;

   @Column(name = "content")
   private String content;

   @Column(name = "created_at")
   @Temporal(TemporalType.TIMESTAMP)
   @DateTimeFormat(pattern = "dd/MM/YYYY hh/mm")
   private Date createdAt;

   @Column(name = "updated_at")
   @Temporal(TemporalType.TIMESTAMP)
   @DateTimeFormat(pattern = "dd/MM/YYYY hh/mm")
   private Date updated_at;

   @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
   private List<Image> images;

   public Post() { }

   public Post(User owner, String title, String content) {
      this.owner = owner;
      this.title = title;
      this.content = content;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public User getOwner() {
      return owner;
   }

   public void setOwner(User owner) {
      this.owner = owner;
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

   public List<Image> getImages() {
      return images;
   }

   public void setImages(List<Image> images) {
      this.images = images;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Post post = (Post) o;
      return id == post.id &&
              Objects.equals(owner, post.owner) &&
              title.equals(post.title) &&
              Objects.equals(content, post.content) &&
              Objects.equals(createdAt, post.createdAt) &&
              Objects.equals(updated_at, post.updated_at);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, owner, title, content, createdAt, updated_at);
   }
}
