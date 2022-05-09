package com.vehicle.identifier.vicore.services;

import com.vehicle.identifier.vicore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vehicle.identifier.vicore.models.User;

import java.util.List;

@Service
public class UserService {


	@Autowired private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRepository userRepository;

	//Get All Users
	public List<User> findAll(){
		return userRepository.findAll();
	}

	//Get User By Id
	public User findById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	//Delete User
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	//Update User
	public void save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
	}

}
