package ru.rabiarill.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rabiarill.dto.model.UserDTO;
import ru.rabiarill.dto.security.JwtTokenDTO;
import ru.rabiarill.dto.security.JwtUserDTO;
import ru.rabiarill.exception.model.user.NotValidUserException;
import ru.rabiarill.model.User;
import ru.rabiarill.service.registration.RegistrationService;
import ru.rabiarill.service.user.UserService;
import ru.rabiarill.util.security.JwtUtil;
import ru.rabiarill.util.validator.UserValidator;

import javax.validation.Valid;

@RestController
@RequestMapping("/social-media/v1/auth")
public class AuthController {

   private final RegistrationService registrationService;
   private final UserService userService;
   private final JwtUtil jwtUtil;
   private final AuthenticationManager authenticationManager;
   private final UserValidator userValidator;

   @Autowired
   public AuthController(RegistrationService registrationService, UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserValidator userValidator) {
      this.registrationService = registrationService;
      this.userService = userService;
      this.jwtUtil = jwtUtil;
      this.authenticationManager = authenticationManager;
      this.userValidator = userValidator;
   }

   @PostMapping("/login")
   public ResponseEntity<JwtTokenDTO> login(@RequestBody @Valid JwtUserDTO userDTO,
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

      JwtTokenDTO response = new JwtTokenDTO(jwtUtil.generateToken(userDTO.getUsername()));

      return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @PostMapping
   public ResponseEntity<JwtTokenDTO> generateJwtToken(@RequestBody @Valid UserDTO userDTO,
                                                       BindingResult bindingResult) {
      User user = userService.convertToUser(userDTO);
      userValidator.validate(user, bindingResult);

      if (bindingResult.hasErrors())
         throw new NotValidUserException(bindingResult.getFieldErrors());

      registrationService.register(user);

      JwtTokenDTO response = new JwtTokenDTO(jwtUtil.generateToken(user.getUsername()));

      return new ResponseEntity<>(response, HttpStatus.CREATED);
   }
}
