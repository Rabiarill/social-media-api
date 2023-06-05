package ru.rabiarill.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rabiarill.dto.model.UserDTO;
import ru.rabiarill.dto.security.JwtTokenDTO;
import ru.rabiarill.exception.model.user.NotValidUserException;
import ru.rabiarill.model.User;
import ru.rabiarill.service.user.UserService;
import ru.rabiarill.util.security.JwtUtil;
import ru.rabiarill.util.validator.UserValidator;

import javax.validation.Valid;

@RestController
@RequestMapping("/social-media/v1/user")
public class UserController {

   private final UserService userService;
   private final AuthenticationManager authenticationManager;
   private final UserValidator userValidator;
   private final JwtUtil jwtUtil;

   @Autowired
   public UserController(UserService userService, AuthenticationManager authenticationManager,
                         UserValidator userValidator, JwtUtil jwtUtil) {
      this.userService = userService;
      this.authenticationManager = authenticationManager;
      this.userValidator = userValidator;
      this.jwtUtil = jwtUtil;
   }

   /**
    * Method that show user information.
    *
    * @param sender
    * @return <code>ResponseEntity</code> with a <code>UserDTO<code> object and the HTTP status
    */
   @GetMapping()
   public ResponseEntity<UserDTO> userInfo(@AuthenticationPrincipal(expression = "user") User sender) {
      return new ResponseEntity<>(sender.convertToDTO(), HttpStatus.OK);
   }

   /**
    * Method validate and update user.
    *
    * @param userDTO
    * @param bindingResult
    * @param sender
    * @return <code>ResponseEntity</code> with a <code>JwtTokenDTO<code> object and the HTTP status
    */
   @PutMapping()
   public ResponseEntity<JwtTokenDTO> update(@RequestBody @Valid UserDTO userDTO,
                                             BindingResult bindingResult,
                                             @AuthenticationPrincipal(expression = "user") User sender) {
      User userToUpdate = userDTO.convertToUser();
      sender.updateFields(userToUpdate);

      userValidator.validate(sender, bindingResult);

      if (bindingResult.hasErrors())
         throw new NotValidUserException(bindingResult.getFieldErrors());

      userService.save(sender);

      JwtTokenDTO response = new JwtTokenDTO(jwtUtil.generateToken(sender.getUsername()));

      return new ResponseEntity<>(response, HttpStatus.OK);
   }

   /**
    * Method check credentials and delete user.
    *
    * @param userDTO
    * @param bindingResult
    * @return <code>ResponseEntity</code> with a the HTTP status
    */
   @DeleteMapping()
   public ResponseEntity<HttpStatus> deleteUser(@RequestBody @Valid UserDTO userDTO,
                                                BindingResult bindingResult) {
      if (bindingResult.hasErrors())
         throw new NotValidUserException(bindingResult.getFieldErrors());

      UsernamePasswordAuthenticationToken authToken =
              new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
      try {
         authenticationManager.authenticate(authToken);
      } catch (BadCredentialsException e) {
         throw new BadCredentialsException("Invalid Credentials");
      }
      userService.deleteById(userDTO.getId());
      return new ResponseEntity<>(HttpStatus.OK);
   }

}
