package ru.rabiarill.exception.model.post;

import org.springframework.validation.FieldError;
import ru.rabiarill.exception.model.ModelFieldException;

import java.util.List;

public class NotValidPostException extends ModelFieldException {
   public NotValidPostException(List<FieldError> fieldErrors) {
      super(fieldErrors);
   }
}
