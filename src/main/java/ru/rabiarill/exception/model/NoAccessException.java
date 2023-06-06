package ru.rabiarill.exception.model;

public class NoAccessException extends RuntimeException {
   public NoAccessException(String message) {
      super(message);
   }
}
