package ru.rabiarill.model.subscriptions;

import ru.rabiarill.model.User;

import javax.persistence.*;

@Entity
@Table(name = "Subscriptions")
@IdClass(SubscriptionsId.class)
public class Subscriptions {

   @Id
   @ManyToOne
   @JoinColumn(name = "subscriber_id", referencedColumnName = "id")
   private User subscriber;

   @Id
   @ManyToOne
   @JoinColumn(name = "subscribed_to_id", referencedColumnName = "id")
   private User subscribedTo;

   public Subscriptions() { }

   public Subscriptions(User subscriber, User subscribedTo) {
      this.subscriber = subscriber;
      this.subscribedTo = subscribedTo;
   }

   public User getSubscriber() {
      return subscriber;
   }

   public void setSubscriber(User subscriber) {
      this.subscriber = subscriber;
   }

   public User getSubscribedTo() {
      return subscribedTo;
   }

   public void setSubscribedTo(User subscribedTo) {
      this.subscribedTo = subscribedTo;
   }
}
