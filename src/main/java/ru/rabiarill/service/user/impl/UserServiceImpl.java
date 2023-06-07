package ru.rabiarill.service.user.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rabiarill.dto.model.UserDTO;
import ru.rabiarill.exception.model.user.UserNotFoundException;
import ru.rabiarill.model.User;
import ru.rabiarill.repository.UserRepository;
import ru.rabiarill.service.user.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   private final ModelMapper modelMapper;

   @Autowired
   public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
      this.userRepository = userRepository;
      this.modelMapper = modelMapper;
   }

   /**
    * @see UserService#findAll() 
    */
   @Override
   public List<User> findAll() {
      return userRepository.findAll();
   }

   /**
    * @see UserService#findById(int)
    */
   @Override
   public User findById(int id) {
      return userRepository
              .findById(id)
              .orElseThrow(() -> new UserNotFoundException("User with id = " + id + " not found"));
   }

   /**
    * @see UserService#findByUsername(String)
    */
   @Override
   public Optional<User> findByUsername(String username) {
      return userRepository.findByUsername(username);
   }

   /**
    * @see UserService#save(User)
    */
   @Override
   @Transactional()
   public User save(User user) {
      return userRepository.save(user);
   }

   /**
    * @see UserService#deleteById(int)
    */
   @Override
   @Transactional()
   public void deleteById(int id) {
      userRepository.deleteById(id);
   }

   /**
    * @see UserService#convertToUser(UserDTO)
    */
   @Override
   public User convertToUser(UserDTO userDTO) {
      return modelMapper.map(userDTO, User.class);
   }

   /**
    * @see UserService#convertListToUser(List)
    */
   @Override
   public List<User> convertListToUser(List<UserDTO> userDTOs){
      return userDTOs
              .stream()
              .map(this::convertToUser)
              .collect(Collectors.toList());
   }

   /**
    * @see UserService#convertToDTO(User)
    */
   @Override
   public UserDTO convertToDTO(User user) {
      return modelMapper.map(user, UserDTO.class);
   }

   /**
    * @see UserService#convertListToDTO(List)
    */
   @Override
   public List<UserDTO> convertListToDTO(List<User> users){
      return users
              .stream()
              .map(this::convertToDTO)
              .collect(Collectors.toList());
   }

}
