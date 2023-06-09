package ru.rabiarill.model;


import ru.rabiarill.model.friend.Friends;
import ru.rabiarill.model.subscriptions.Subscriptions;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Users")
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private int id;

   @Column(name = "username")
   private String username;

   @Column(name = "email")
   private String email;

   @Column(name = "password")
   private String password;

   @OneToMany(mappedBy = "owner")
   private List<Post> posts;

   @OneToMany(mappedBy = "subscribedTo", cascade = CascadeType.ALL)
   private List<Subscriptions> subscribed;

   @OneToMany(mappedBy = "friendTwo", cascade = CascadeType.ALL)
   private List<Friends> friends;

   public User() { }

   public User(int id, String username, String email, String password) {
      this.id = id;
      this.username = username;
      this.email = email;
      this.password = password;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public List<Post> getPosts() {
      return posts;
   }

   public void setPosts(List<Post> posts) {
      this.posts = posts;
   }

   public List<Subscriptions> getSubscribed() {
      return subscribed;
   }

   public void setSubscribed(List<Subscriptions> subscribed) {
      this.subscribed = subscribed;
   }

   public List<Friends> getFriends() {
      return friends;
   }

   public void setFriends(List<Friends> friends) {
      this.friends = friends;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return id == user.id &&
              username.equals(user.username) &&
              email.equals(user.email);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, username, email);
   }

   public void updateFields(User newUser){
      this.username = newUser.getUsername();
      this.email = newUser.getEmail();
   }

}
