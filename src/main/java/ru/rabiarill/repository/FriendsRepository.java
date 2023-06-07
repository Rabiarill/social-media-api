package ru.rabiarill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rabiarill.model.User;
import ru.rabiarill.model.friend.Friends;
import ru.rabiarill.model.friend.FriendsId;

import java.util.List;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, FriendsId> {
   List<Friends> findAllByFriend(User user);
}
