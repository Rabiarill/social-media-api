package ru.rabiarill.util.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.rabiarill.model.User;
import ru.rabiarill.service.user.UserService;

import java.util.Optional;

@Component
public class UserValidator implements Validator {
   private final UserService userService;

   @Autowired
   public UserValidator(UserService userService) {
      this.userService = userService;
   }

   @Override
   public boolean supports(Class<?> aClass) {
      return User.class.equals(aClass);
   }

   @Override
   public void validate(Object o, Errors errors) {
      User target = (User) o;
      Optional<User> dbUser = userService.findByUsername(target.getUsername());

      if (dbUser.isPresent() && target.getId() != dbUser.get().getId())
         errors.rejectValue("username", "", target.getUsername() + " is exist");
   }

}
