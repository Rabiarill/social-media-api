package ru.rabiarill.model.friend;

import java.io.Serializable;

public class FriendsId implements Serializable {

   private int friend;
   private int friendTwo;

   public FriendsId() { }

   public int getFriendId() {
      return friend;
   }

   public void setFriendId(int friendId) {
      this.friend = friendId;
   }

   public int getFriendTwoId() {
      return friendTwo;
   }

   public void setFriendTwoId(int friendTwoId) {
      this.friendTwo = friendTwoId;
   }
}
