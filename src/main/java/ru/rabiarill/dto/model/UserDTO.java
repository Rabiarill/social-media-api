package ru.rabiarill.dto.model;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDTO {

   private int id;

   @NotEmpty(message = "Username should not be empty")
   @Size(min = 4, max = 255, message = "Username size should be between 4 and 255")
   private String username;

   @Email(message = "Email should be valid")
   private String email;

   @Size(min = 4, message = "Password size should be more than 4")
   private String password;

   public UserDTO() { }

   public UserDTO(int id, String username, String email, String password) {
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

}
