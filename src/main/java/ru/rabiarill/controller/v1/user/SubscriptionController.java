package ru.rabiarill.controller.v1.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.rabiarill.dto.model.UserDTO;
import ru.rabiarill.model.User;
import ru.rabiarill.service.subscription.SubscriptionService;
import ru.rabiarill.service.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/social-media/v1/user/subscription")
public class SubscriptionController {

   private final SubscriptionService subscriptionService;
   private final UserService userService;

   @Autowired
   public SubscriptionController(SubscriptionService subscriptionService, UserService userService) {
      this.subscriptionService = subscriptionService;
      this.userService = userService;
   }

   /**
    * Method that show all user subscribers.
    *
    * @param sender
    * @return <code>ResponseEntity</code> with a <code>List<UserDTO><code> object and the HTTP status
    */
   @GetMapping()
   public ResponseEntity<List<UserDTO>> findSubscribers(@AuthenticationPrincipal(expression = "user") User sender){
      List<UserDTO> response = userService.convertListToDTO(subscriptionService.findSubscribers(sender));

      return new ResponseEntity<>(response, HttpStatus.OK);
   }

   /**
    * Method that show all person that user subscribed to.
    *
    * @param sender
    * @return <code>ResponseEntity</code> with a <code>List<UserDTO><code> object and the HTTP status
    */
   @GetMapping("/to")
   public ResponseEntity<List<UserDTO>> findSubscribedTo(@AuthenticationPrincipal(expression = "user") User sender){
      List<UserDTO> response = userService.convertListToDTO(subscriptionService.findSubscribedTo(sender));

      return new ResponseEntity<>(response, HttpStatus.OK);
   }

   /**
    * Method subscribe sender to user by id.
    *
    * @param id
    * @param sender
    * @return <code>ResponseEntity</code> with the HTTP status
    */
   @PostMapping("/{id}")
   public ResponseEntity<HttpStatus> subscribe(@PathVariable("id") int id,
                                               @AuthenticationPrincipal(expression = "user") User sender) {
      User subscribedTo = userService.findById(id);

      subscriptionService.subscribe(sender, subscribedTo);

      return new ResponseEntity<>(HttpStatus.ACCEPTED);
   }

   /**
    * Method unsubscribe sender to user by id.
    *
    * @param id
    * @param sender
    * @return <code>ResponseEntity</code> with the HTTP status
    */
   @DeleteMapping("/{id}")
   public ResponseEntity<HttpStatus> unsubscribe(@PathVariable("id") int id,
                                                 @AuthenticationPrincipal(expression = "user") User sender) {
      User unsubscribedTo = userService.findById(id);

      subscriptionService.unsubscribe(sender, unsubscribedTo);

      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
}
