package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.User;
import repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public User save(User user) {
		return userRepository.save(user);
	}

	public void remove(Long id) {
		userRepository.deleteById(id);
	}
}
