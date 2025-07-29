package com.shopit.admin.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopit.admin.ShopitBackEndApplication;
import com.shopit.admin.user.service.UserService;
import com.shopit.common.entity.Role;
import com.shopit.common.entity.User;

@Controller
public class UserController {

    private final ShopitBackEndApplication shopitBackEndApplication;
	
	@Autowired
	private UserService service;

    UserController(ShopitBackEndApplication shopitBackEndApplication) {
        this.shopitBackEndApplication = shopitBackEndApplication;
    }
	
	@GetMapping("/users")
	public String listAll(Model model) {
		List<User> listAllUsers = service.listAllUsers();
		List<Role> listAllRoles = service.listAllRoles();
		model.addAttribute("listAllUsers",listAllUsers);
		model.addAttribute("listAllRoles",listAllRoles);
		return "users";
	}
	
	@GetMapping("/users/new")
	public String createUser(Model model) {
		User user = new User(); 
		List<Role> listAllRoles = service.listAllRoles();
		model.addAttribute("user", user);
		model.addAttribute("listAllRoles", listAllRoles);
		return "user_form";
	}

	
	@PostMapping("/users/save")
	public String saveUser(@ModelAttribute("user") User user,
	                       RedirectAttributes redirectAttributes,
	                       Model model) {

	    if (service.isEmailUnique(user.getEmail())) {
	        service.save(user);
	        redirectAttributes.addFlashAttribute("message", "The User has been saved successfully !!");
	        return "redirect:/users";
	    } else {
	        model.addAttribute("duplicateEmailmessage", "Email is already registered, use a different email !!");
	        model.addAttribute("listAllRoles", service.listAllRoles());
	        return "user_form";  // No redirect
	    }
	}


}
