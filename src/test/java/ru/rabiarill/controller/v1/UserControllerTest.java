package ru.rabiarill.controller.v1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import ru.rabiarill.dto.model.UserDTO;
import ru.rabiarill.exception.model.user.NotValidUserException;
import ru.rabiarill.model.User;
import ru.rabiarill.service.user.UserService;
import ru.rabiarill.util.security.JwtUtil;
import ru.rabiarill.util.validator.UserValidator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

   @Mock
   UserService userService;
   @Mock
   AuthenticationManager authenticationManager;
   @Mock
   UserValidator userValidator;
   @Mock
   JwtUtil jwtUtil;
   @Mock
   BindingResult mockBindingResult;

   @InjectMocks
   UserController userController;


   @Test
   void userInfo_ValidUser_ReturnValidResponse(){
      // given
      User user = getMockUser();

      // when
      var response = this.userController.userInfo(user);

      // then
      assertNotNull(response);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertNotNull(response.getBody());
      assertEquals(user, response.getBody().convertToUser());

   }

   @Test
   void update_ValidUser_ReturnValidResponseAndSaveUser() {
      // given
      User user = getMockUser();

      // when
      var response = this.userController.update(user.convertToDTO(), mockBindingResult, user);

      // then
      assertNotNull(response);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertNotNull(response.getBody());
      verify(userService, only()).save(user);

   }

   @Test
   void update_NotValidUser_ThrowNotValidUserException() {
      when(mockBindingResult.hasErrors()).thenReturn(true);

      assertThrows(NotValidUserException.class,
              () -> this.userController.update(new UserDTO(), mockBindingResult, getMockUser()));
      verifyNoInteractions(userService);

   }

   @Test
   void deleteUser_ValidUser_ReturnValidResponseAndDeleteUser() {
      // given
      UserDTO userDTO = getMockUser().convertToDTO();

      // when
      var response = this.userController.deleteUser(userDTO, mockBindingResult);

      // then
      assertNotNull(response);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertNull(response.getBody());
      verify(userService, only()).deleteById(userDTO.getId());

   }

   @Test
   void deleteUser_NotValidUser_ThrowNotValidUserException() {
      when(mockBindingResult.hasErrors()).thenReturn(true);

      assertThrows(NotValidUserException.class,
              () -> this.userController.deleteUser(new UserDTO(), mockBindingResult));
      verifyNoInteractions(userService);
      verifyNoInteractions(authenticationManager);

   }

   @Test
   void deleteUser_WithInvalidCredentials_ThrowBadCredentialsException() {
      UserDTO userDTO = getMockUser().convertToDTO();
      when(authenticationManager.authenticate(any())).thenThrow(BadCredentialsException.class);

      assertThrows(BadCredentialsException.class,
              () -> this.userController.deleteUser(userDTO, mockBindingResult));
      verifyNoInteractions(userService);

   }

   private User getMockUser(){
      return new User(1, "Doe", "doe@mail.com", "1");
   }

}