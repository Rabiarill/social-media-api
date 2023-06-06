package ru.rabiarill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rabiarill.model.subscriptions.Subscriptions;
import ru.rabiarill.model.subscriptions.SubscriptionsId;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscriptions, SubscriptionsId> {
}
