package ru.rabiarill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.rabiarill.model.User;
import ru.rabiarill.model.subscriptions.Subscriptions;
import ru.rabiarill.model.subscriptions.SubscriptionsId;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscriptions, SubscriptionsId> {
   List<Subscriptions> findAllBySubscriber(User subscriber);
   List<Subscriptions> findAllBySubscribedTo(User subscriber);
   @Query(nativeQuery = true,
           value = "select exists(" +
           "    select * from subscriptions" +
           "    where (subscriber_id = :userId and  subscribed_to_id = :userIdTwo)" +
           "         or (subscriber_id = :userIdTwo and  subscribed_to_id = :userId));")
   boolean hasMatch(int userId, int userIdTwo);
}
