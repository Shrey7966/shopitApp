package com.shopit.admin.user.restcotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopit.admin.user.service.UserService;

@RestController
public class UserRestController {
	
	@Autowired
	public UserService userService;
	
	@PostMapping("/users/check_email")
	public String isEmailduplicate(@Param("email") String email) {
		return userService.isEmailUnique(email)?"OK":"DUPLICATED";
	}

}
