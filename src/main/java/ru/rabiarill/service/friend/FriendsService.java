package ru.rabiarill.service.friend;

import ru.rabiarill.model.User;

import java.util.List;

public interface FriendsService {

   /**
    * Method find all friends of user.
    *
    * @param user
    * @return <code>List<User></code> object
    */
   List<User> findFriends(User user);


   /**
    * Method checks if there are mutual subscriptions and add friend in db.
    *
    * @param friendOne
    * @param friendTwo
    */
   void addFriend(User friendOne, User friendTwo);

   /**
    * The method unsubscribe the user and deletes the relation in the database.
    *
    * @param user
    * @param friendToDelete
    */
   void deleteFriend(User user, User friendToDelete);
}
