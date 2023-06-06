package ru.rabiarill.service.subscription.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rabiarill.model.User;
import ru.rabiarill.model.subscriptions.Subscriptions;
import ru.rabiarill.repository.SubscriptionRepository;
import ru.rabiarill.service.subscription.SubscriptionService;

@Service("subscriptionService")
@Transactional(readOnly = true)
public class SubscriptionServiceImpl implements SubscriptionService {

   private final SubscriptionRepository subscriptionRepository;

   @Autowired
   public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
      this.subscriptionRepository = subscriptionRepository;
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
}
