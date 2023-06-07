package ru.rabiarill.model.friend;

import ru.rabiarill.model.User;

import javax.persistence.*;

@Entity
@Table(name = "Friends")
@IdClass(FriendsId.class)
public class Friends {

   @Id
   @ManyToOne
   @JoinColumn(name = "friend_id", referencedColumnName = "id")
   private User friend;

   @Id
   @ManyToOne
   @JoinColumn(name = "friend_second_id", referencedColumnName = "id")
   private User friendTwo;

   public Friends() { }

   public Friends(User friend, User friendTwo) {
      this.friend = friend;
      this.friendTwo = friendTwo;
   }

   public User getFriend() {
      return friend;
   }

   public void setFriend(User friend) {
      this.friend = friend;
   }

   public User getFriendTwo() {
      return friendTwo;
   }

   public void setFriendTwo(User friendTwo) {
      this.friendTwo = friendTwo;
   }
}
