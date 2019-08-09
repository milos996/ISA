package com.example.ISAums.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISAums.model.User;
import com.example.ISAums.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){

    	this.userRepository = userRepository;
	}


	public User save(User user) {
		return userRepository.save(user);
	}

}
