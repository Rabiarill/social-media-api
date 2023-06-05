package ru.rabiarill.dto.security;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class JwtUserDTO {

   @NotEmpty(message = "Username should not be empty")
   @Size(min = 4, max = 255, message = "Username size should be between 4 and 255")
   private String username;

   @Size(min = 4, message = "Password size should be more than 4")
   private String password;

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

}
