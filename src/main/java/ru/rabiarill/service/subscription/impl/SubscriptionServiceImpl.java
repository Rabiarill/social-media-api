package ru.rabiarill.service.subscription.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rabiarill.model.User;
import ru.rabiarill.model.subscriptions.Subscriptions;
import ru.rabiarill.repository.SubscriptionRepository;
import ru.rabiarill.service.subscription.SubscriptionService;

import java.util.List;
import java.util.stream.Collectors;

@Service("subscriptionService")
@Transactional(readOnly = true)
public class SubscriptionServiceImpl implements SubscriptionService {

   private final SubscriptionRepository subscriptionRepository;

   @Autowired
   public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
      this.subscriptionRepository = subscriptionRepository;
   }


   /**
    * @see SubscriptionService#findSubscribers(User)
    */
   @Override
   public List<User> findSubscribers(User user) {
      return subscriptionRepository
              .findAllBySubscribedTo(user)
              .stream()
              .map(Subscriptions::getSubscribedTo)
              .collect(Collectors.toList());
   }

   /**
    * @see SubscriptionService#findSubscribedTo(User)
    */
   @Override
   public List<User> findSubscribedTo(User user) {
      return subscriptionRepository
              .findAllBySubscriber(user)
              .stream()
              .map(Subscriptions::getSubscribedTo)
              .collect(Collectors.toList());
   }

   /**
    * @see SubscriptionService#subscribe(User, User)
    */
   @Override
   @Transactional
   public void subscribe(User subscriber, User subscribedTo) {
      subscriptionRepository.save(new Subscriptions(subscriber, subscribedTo));
   }

   /**
    * @see SubscriptionService#unsubscribe(User, User)
    */
   @Override
   @Transactional
   public void unsubscribe(User subscriber, User unsubscribedTo) {
      subscriptionRepository.delete(new Subscriptions(subscriber, unsubscribedTo));
   }

   /**
    * @see SubscriptionService#hasMatch(User, User)
    */
   @Override
   public boolean hasMatch(User userOne, User userTwo) {
      return subscriptionRepository.hasMatch(userOne.getId(), userTwo.getId());
   }
}
