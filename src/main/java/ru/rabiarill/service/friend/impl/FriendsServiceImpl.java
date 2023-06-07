package ru.rabiarill.service.friend.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rabiarill.exception.model.NoMatchException;
import ru.rabiarill.model.User;
import ru.rabiarill.model.friend.Friends;
import ru.rabiarill.repository.FriendsRepository;
import ru.rabiarill.service.friend.FriendsService;
import ru.rabiarill.service.subscription.SubscriptionService;

import java.util.List;
import java.util.stream.Collectors;

@Service("friendService")
@Transactional(readOnly = true)
public class FriendsServiceImpl implements FriendsService {

   private final FriendsRepository friendsRepository;
   private final SubscriptionService subscriptionService;

   @Autowired
   public FriendsServiceImpl(FriendsRepository friendsRepository, SubscriptionService subscriptionService) {
      this.friendsRepository = friendsRepository;
      this.subscriptionService = subscriptionService;
   }

   /**
    * @see FriendsService#findFriends(User)
    */
   @Override
   public List<User> findFriends(User user) {
      return friendsRepository.findAllByFriend(user)
              .stream()
              .map(Friends::getFriendTwo)
              .collect(Collectors.toList());
   }

   /**
    * @see FriendsService#addFriend(User, User)
    */
   @Override
   @Transactional
   public void addFriend(User friendOne, User friendTwo) {
      if (subscriptionService.hasMatch(friendOne, friendTwo))
         friendsRepository.save(new Friends(friendOne, friendTwo));
      else
         throw new NoMatchException("Users has no match subscription");
   }

   /**
    * @see FriendsService#deleteFriend(User, User)
    */
   @Override
   @Transactional
   public void deleteFriend(User user, User friendToDelete) {
      subscriptionService.unsubscribe(user, friendToDelete);
      friendsRepository
              .deleteAllInBatch(
                      List.of(new Friends(user, friendToDelete),
                              new Friends(friendToDelete, user)));
   }
}
