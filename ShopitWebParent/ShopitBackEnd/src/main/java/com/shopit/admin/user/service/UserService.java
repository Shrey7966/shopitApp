package com.shopit.admin.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopit.admin.user.repository.RoleRepository;
import com.shopit.admin.user.repository.UserRepository;
import com.shopit.common.entity.Role;
import com.shopit.common.entity.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> listAllUsers() {
		return (List<User>) userRepo.findAll();

	}

	public List<Role> listAllRoles() {
		return (List<Role>) roleRepo.findAll();
	}

	public void save(User user) {
		encodePassword(user);
		userRepo.save(user);
	}

	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	}
	
	public boolean isEmailUnique(String email) {
		User userByEmail = userRepo.getUserByEmail(email);
		return userByEmail==null;
	}

	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(id).get();
		return user;
	}
	
	public void updateAndSave(User user) {
		userRepo.save(user);
	}

	public void deleteUser(Integer id) {
		userRepo.deleteById(id);
		
	}
	
	

}
