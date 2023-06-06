package ru.rabiarill.model.subscriptions;

import java.io.Serializable;

public class SubscriptionsId implements Serializable {

   private int subscriber;
   private int subscribedTo;

   public SubscriptionsId() { }

   public int getSubscriber() {
      return subscriber;
   }

   public void setSubscriber(int subscriber) {
      this.subscriber = subscriber;
   }

   public int getSubscribedTo() {
      return subscribedTo;
   }

   public void setSubscribedTo(int subscribedTo) {
      this.subscribedTo = subscribedTo;
   }
}
