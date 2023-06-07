package ru.rabiarill.controller.v1.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.rabiarill.dto.model.UserDTO;
import ru.rabiarill.model.User;
import ru.rabiarill.service.friend.FriendsService;
import ru.rabiarill.service.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/social-media/v1/user/friends")
public class FriendsController {

   private final FriendsService friendsService;
   private final UserService userService;

   @Autowired
   public FriendsController(FriendsService friendsService, UserService userService) {
      this.friendsService = friendsService;
      this.userService = userService;
   }

   /**
    * Method find all friends of sender.
    *
    * @param sender
    * @return <code>ResponseEntity</code> with a <code>List<UserDTO><code> object and the HTTP status
    */
   @GetMapping
   public ResponseEntity<List<UserDTO>> findFriends(@AuthenticationPrincipal(expression = "user") User sender){
      List<User> users = friendsService.findFriends(sender);

      return new ResponseEntity<>(userService.convertListToDTO(users), HttpStatus.OK);
   }

   /**
    * Method add friends if they hav match subscriptions.
    *
    * @param id
    * @param sender
    * @return <code>ResponseEntity</code> with a the HTTP status
    */
   @PostMapping("/{id}")
   public ResponseEntity<HttpStatus> addFriend(@PathVariable("id") int id,
                                               @AuthenticationPrincipal(expression = "user") User sender){
      User friendToAdd = userService.findById(id);
      friendsService.addFriend(sender, friendToAdd);

      return new ResponseEntity<>(HttpStatus.ACCEPTED);
   }

   /**
    * Method deletes friend relation and unsubscribe sender. The person who is deleted and remains subscribed.
    *
    * @param id
    * @param sender
    * @return <code>ResponseEntity</code> with a the HTTP status
    */
   @DeleteMapping("/{id}")
   public ResponseEntity<HttpStatus> deleteFriend(@PathVariable("id") int id,
                                               @AuthenticationPrincipal(expression = "user") User sender){
      User friendToDelete = userService.findById(id);
      friendsService.deleteFriend(sender,friendToDelete);

      return new ResponseEntity<>(HttpStatus.ACCEPTED);
   }
}
