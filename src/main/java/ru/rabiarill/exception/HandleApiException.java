package ru.rabiarill.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.rabiarill.dto.response.ErrResponseDTO;
import ru.rabiarill.exception.model.ModelFieldException;
import ru.rabiarill.exception.model.NoAccessException;
import ru.rabiarill.exception.model.post.PostNotFoundException;
import ru.rabiarill.exception.model.user.UserNotFoundException;

@ControllerAdvice
public class HandleApiException {

   @ExceptionHandler(value = {ModelFieldException.class})
   public ResponseEntity<ErrResponseDTO> handleNotValidModel(ModelFieldException e) {
      ErrResponseDTO errDTO = new ErrResponseDTO(e.getFieldErrors());

      return new ResponseEntity<>(errDTO, HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler(value = {PostNotFoundException.class, UserNotFoundException.class})
   public ResponseEntity<ErrResponseDTO> handleNotFound(Exception e){
      ErrResponseDTO errDTO = new ErrResponseDTO(e);

      return new ResponseEntity<>(errDTO, HttpStatus.NOT_FOUND);
   }

   @ExceptionHandler(value = {NoAccessException.class})
   public ResponseEntity<ErrResponseDTO> handleNoAccess(NoAccessException e) {
      ErrResponseDTO errDTO = new ErrResponseDTO(e);

      return new ResponseEntity<>(errDTO, HttpStatus.FORBIDDEN);
   }

   @ExceptionHandler(value = {Exception.class})
   public ResponseEntity<ErrResponseDTO> handleException(Exception e) {
      ErrResponseDTO errDTO = new ErrResponseDTO(e);

      return new ResponseEntity<>(errDTO, HttpStatus.BAD_REQUEST);
   }


}
