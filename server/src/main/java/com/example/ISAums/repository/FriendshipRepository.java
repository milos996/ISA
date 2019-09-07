package com.example.ISAums.repository;

import com.example.ISAums.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface FriendshipRepository extends JpaRepository<Friendship, UUID> {

    @Query(value = "select u.id  from friendship f left join user u on u.id = (case when f.user_sender_id = ?1 then f.user_invited_id " +
            "when f.user_invited_id = ?1 then f.user_sender_id  end) where u.id != ?1 and f.invitation_status = 'ACCEPTED'", nativeQuery = true)
    List<UUID> getFriends(String user_id);

    @Modifying
    @Query(value = "delete from friendship where friendship.user_sender_id = ?1 and friendship.user_invited_id = ?2 or friendship.user_sender_id = ?2 and friendship.user_invited_id = ?1", nativeQuery = true)
    void removeFriendship(String mine_id, String friend_id);
}