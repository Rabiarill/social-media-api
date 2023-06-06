package ru.rabiarill.service.subscription;

import ru.rabiarill.model.User;

public interface SubscriptionService {

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
}
