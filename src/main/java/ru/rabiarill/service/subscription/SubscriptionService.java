package ru.rabiarill.service.subscription;

import ru.rabiarill.model.User;

import java.util.List;

public interface SubscriptionService {

   /**
    * Method find all subscribers of user.
    *
    * @param user
    * @return <code>List<User></code> object
    */
    List<User> findSubscribers(User user);

   /**
    * Method find all persons that user subscribed.
    *
    * @param user
    * @return <code>List<User></code> object
    */
    List<User> findSubscribedTo(User user);

   /**
    * Method subscribe first user to second user.
    *
    * @param subscriber
    * @param subscribedTo
    */
   void subscribe(User subscriber, User subscribedTo);

   /**
    * Method unsubscribe first user to second user.
    *
    * @param subscriber
    * @param unsubscribedTo
    */
   void unsubscribe(User subscriber, User unsubscribedTo);

   /**
    * Method check match subscription.
    *
    * @param userOne
    * @param userTwo
    * @return True if user have match subscription
    */
   boolean hasMatch(User userOne, User userTwo);
}
