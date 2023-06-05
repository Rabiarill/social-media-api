package ru.rabiarill.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rabiarill.exception.model.user.UserNotFoundException;
import ru.rabiarill.model.User;
import ru.rabiarill.repository.UserRepository;
import ru.rabiarill.service.user.UserService;

import java.util.List;
import java.util.Optional;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;

   @Autowired
   public UserServiceImpl(UserRepository userRepository) {
      this.userRepository = userRepository;
   }


   @Override
   public List<User> findAll() {
      return userRepository.findAll();
   }

   @Override
   public User findById(int id) {
      return userRepository
              .findById(id)
              .orElseThrow(() -> new UserNotFoundException("User with id = " + id + " not found"));
   }

   @Override
   public Optional<User> findByUsername(String username) {
      return userRepository.findByUsername(username);
   }

   @Override
   @Transactional()
   public User save(User user) {
      return userRepository.save(user);
   }

   @Override
   @Transactional()
   public void deleteById(int id) {
      userRepository.deleteById(id);
   }
}
