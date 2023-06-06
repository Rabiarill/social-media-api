package ru.rabiarill.service.user;


import ru.rabiarill.dto.model.UserDTO;
import ru.rabiarill.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

   /**
    * Method that find all user.
    *
    * @return <code>List<User></code> object
    */
   List<User> findAll();

   /**
    * Method that find use by id.
    *
    * @param id
    * @return <code>User</code> object
    */
   User findById(int id);

   /**
    * Method that find user by username.
    *
    * @param username
    * @return <code>Optional<User></code> object
    */
   Optional<User> findByUsername(String username);

   /**
    * Method that save an object User.
    *
    * @param user
    * @return <code>User</code> object
    */
   User save(User user);

   /**
    * Method that remove an object User by an id.
    *
    * @param id
    */
   void deleteById(int id);

   /**
    * Method that convert UserDTO object to User object.
    *
    * @param userDTO
    * @return <code>User</code> object
    */
   User convertToUser(UserDTO userDTO);

   /**
    * Method that convert User object to UserDTO object.
    *
    * @param user
    * @return
    */
   UserDTO convertToDTO(User user);

}
