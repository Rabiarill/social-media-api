package ru.rabiarill.exception.model.post;

public class PostNotFoundException extends RuntimeException {
   public PostNotFoundException() {
   }

   public PostNotFoundException(String message) {
      super(message);
   }

   public PostNotFoundException(String message, Throwable cause) {
      super(message, cause);
   }
}
