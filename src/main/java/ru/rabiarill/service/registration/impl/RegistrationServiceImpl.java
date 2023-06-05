package ru.rabiarill.service.registration.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rabiarill.model.User;
import ru.rabiarill.service.registration.RegistrationService;
import ru.rabiarill.service.user.UserService;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {

   private final UserService userService;
   private final PasswordEncoder passwordEncoder;

   @Autowired
   public RegistrationServiceImpl(UserService userService, PasswordEncoder passwordEncoder) {
      this.userService = userService;
      this.passwordEncoder = passwordEncoder;
   }

   /**
    * @see RegistrationService#register(User)
    */
   @Override
   @Transactional
   public void register(User user) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userService.save(user);
   }
}
