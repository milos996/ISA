package com.example.ISAums.service;

import com.example.ISAums.exception.CustomException;
import com.example.ISAums.dto.request.SendFriendshipRequestRequest;
import com.example.ISAums.dto.request.UpdateUserProfileRequest;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.Friendship;
import com.example.ISAums.model.enumeration.InvitationStatus;
import com.example.ISAums.repository.FriendshipRepository;
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
    private final UserRepository userRepository;
	private final FriendshipRepository friendshipRepository;

	public UserService(UserRepository userRepository, FriendshipRepository friendshipRepository){
		this.userRepository = userRepository;
		this.friendshipRepository = friendshipRepository;
	}

    @Transactional(rollbackFor = Exception.class)
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return  userRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean existsByPhoneNumber(String phoneNumber) {
        return  userRepository.existsByPhoneNumber(phoneNumber);
    }

	@Transactional(rollbackFor = Exception.class)
    public User updateUser(UpdateUserProfileRequest request) {

		if(!userRepository.existsById(request.getId())){
			throw new EntityWithIdDoesNotExist("User", request.getId());
		}

		if(userRepository.findByEmail(request.getEmail()) != null){
			throw new CustomException("Email " + request.getEmail() + " already exist.");
		}

		Optional<User> user = userRepository.findById(request.getId());
		copyNonNullProperties(request, user.get());
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

	public Friendship sendFriendshipRequest(SendFriendshipRequestRequest request) {

        Optional<User> sender = userRepository.findById(request.getSenderUserId());
        Optional<User> invitedUser = userRepository.findById(request.getInvitedUserId());

		Friendship friendship = Friendship.builder()
				.sender(sender.get())
				.invitedUser(invitedUser.get())
				.invitationStatus(InvitationStatus.PENDING)
				.build();

		friendshipRepository.save(friendship);

		return friendship;
	}

	@Transactional(rollbackFor = Exception.class)
    public void removeFriend(UUID mine_id, UUID friend_id) {
		friendshipRepository.removeFriendship(String.valueOf(mine_id), String.valueOf(friend_id));
    }

    public List<User> search(String name) {
		return userRepository.findAllByFirstName(name);
    }

	public void updatePassword(String newPassword) {
		//...
	}

}
