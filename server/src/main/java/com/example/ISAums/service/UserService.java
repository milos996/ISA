package com.example.ISAums.service;

import com.example.ISAums.dto.request.SendFriendshipRequestRequest;
import com.example.ISAums.dto.request.UpdateUserProfileRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityAlreadyExistsException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.Friendship;
import com.example.ISAums.model.enumeration.InvitationStatus;
import com.example.ISAums.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISAums.model.User;
import com.example.ISAums.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.ISAums.util.UtilService.copyNonNullProperties;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FriendshipRepository friendshipRepository;

	public User save(User user) {
		return userRepository.save(user);
	}


	@Transactional(rollbackFor = Exception.class)
    public User updateUser(UpdateUserProfileRequest req) {

		if(!userRepository.existsById(req.getId())){
			throw new EntityWithIdDoesNotExist("User", req.getId());
		}

		if(userRepository.findByEmail(req.getEmail()) != null){
			throw new CustomException("Email " + req.getEmail() + " already exist.");
		}
		Optional<User> user = userRepository.findById(req.getId());
		copyNonNullProperties(req, user.get());
		save(user.get());
		return user.get();

	}
	public List<User> getListOfFriends(UUID user_id){

		List<UUID> ids = friendshipRepository.getFriends(user_id.toString());
		List<User> friends = new ArrayList<User>(ids.size());

		for(int i = 0; i < ids.size(); i++){
			Optional<User> tmp = userRepository.findById(UUID.fromString(String.valueOf(ids.get(i))));
			friends.add(tmp.get());
		}

		return friends;

	}

	public Friendship sendFriendshipRequest(SendFriendshipRequestRequest req) {

        Optional<User> sender = userRepository.findById(req.getSender().getId());
        copyNonNullProperties(req.getSender(), sender.get());
        userRepository.save(sender.get());

        Optional<User> invitedUser = userRepository.findById(req.getInvitedUser().getId());
        copyNonNullProperties(req.getInvitedUser(), invitedUser.get());
        userRepository.save(invitedUser.get());

		Friendship friendship = Friendship.builder()
				.sender(sender.get())
				.invitedUser(invitedUser.get())
				.invitationStatus(req.getInvitationStatus())
				.build();

		friendshipRepository.save(friendship);
		return friendship;
	}
	@Transactional(rollbackFor = Exception.class)
    public User removeFriend(UUID mine_id, UUID friend_id) {

		friendshipRepository.removeFriendship(String.valueOf(mine_id), String.valueOf(friend_id));
		return userRepository.findById(friend_id).get();

    }
}
