package ru.rabiarill.dto.response;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ErrResponseDTO {

   private List<ErrMessage> errors = new ArrayList<>();

   public ErrResponseDTO(Exception e) {
      this.errors.add(new ErrMessage(e.getMessage(), new Date()));
   }

   public ErrResponseDTO(List<FieldError> fieldErrors) {
      this.errors.addAll(
              fieldErrors.stream()
                      .map(e -> new ErrMessage(e.getField() + " - " + e.getDefaultMessage(), new Date()))
                      .collect(Collectors.toList()));
   }

   public void addError(Exception e) {
      this.errors.add(new ErrMessage(e.getMessage(), new Date()));
   }

   public List<ErrMessage> getErrors() {
      return errors;
   }

   public void setErrors(List<ErrMessage> errors) {
      this.errors = errors;
   }


}
