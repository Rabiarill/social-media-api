package ru.rabiarill.service.registration;

import ru.rabiarill.model.User;

public interface RegistrationService {

   /**
    * Method register user in system.
    *
    * @param user
    */
   void register(User user);
}
