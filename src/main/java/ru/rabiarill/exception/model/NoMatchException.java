package ru.rabiarill.exception.model;

public class NoMatchException extends RuntimeException {
   public NoMatchException() {
   }

   public NoMatchException(String message) {
      super(message);
   }

   public NoMatchException(String message, Throwable cause) {
      super(message, cause);
   }
}
