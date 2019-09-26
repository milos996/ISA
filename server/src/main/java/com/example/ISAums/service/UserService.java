package com.example.ISAums.service;

import com.example.ISAums.dto.request.UpdateFriendshipRequestRequest;
import com.example.ISAums.dto.request.SendFriendshipRequestRequest;
import com.example.ISAums.dto.request.UpdateUserProfileRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.Friendship;
import com.example.ISAums.model.enumeration.InvitationStatus;
import com.example.ISAums.repository.FriendshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
	private final FriendshipRepository friendshipRepository;

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

	public Friendship sendFriendshipRequest(UUID userId,  SendFriendshipRequestRequest request) {

        Optional<User> sender = userRepository.findById(userId);
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

    public List<User> search(UUID mine_id, String name) {
		List<User> allUsers = userRepository.findAllByFirstName(name);
		List<User> usersWhichAreNotFriends = new ArrayList<>();

		for (User u:allUsers) {
			Friendship friendship = friendshipRepository.isItFriendOfMine(String.valueOf(mine_id), String.valueOf(u.getId()));
			if(friendship == null)
				usersWhichAreNotFriends.add(u);
			else
				continue;
		}
		return usersWhichAreNotFriends;
    }

	public void updatePassword(String newPassword, UUID userId) {
		Optional<User> user = userRepository.findById(userId);
		user.get().setPassword(newPassword);
		userRepository.save(user.get());
	}

	public User findById(UUID user_id) {
		return userRepository.findById(user_id).get();
	}

	public List<Friendship> getFriendshipRequests(UUID mine_id) {
		return friendshipRepository.getFriendshipRequestsOfMine(String.valueOf(mine_id));
	}

	public void updateFriendshipRequest(UpdateFriendshipRequestRequest request) {
		Optional<Friendship> friendship = friendshipRepository.findById(request.getId());
		copyNonNullProperties(request, friendship.get());
		friendshipRepository.save(friendship.get());
	}

	@Transactional(readOnly = true)
	public List<User> sort(String by) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email =  authentication.getName();

		User user = userRepository.findByEmail(email);

		if (by.equals("firstName"))
			return friendshipRepository.sortByFirstName(user.getId().toString());
		else if(by.equals("lastName"))
			return friendshipRepository.sortByLastName(user.getId().toString());
		else
			throw  new CustomException("Unknown attribute!");

	}


}
