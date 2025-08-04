package com.shopit.admin.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopit.admin.ShopitBackEndApplication;
import com.shopit.admin.user.repository.UserRepository;
import com.shopit.admin.user.service.UserService;
import com.shopit.common.entity.Role;
import com.shopit.common.entity.User;

@Controller
public class UserController {

    private final UserRepository userRepository;

	private final ShopitBackEndApplication shopitBackEndApplication;

	@Autowired
	private UserService service;

	@Autowired
	private PasswordEncoder passwordEncoder;

	UserController(ShopitBackEndApplication shopitBackEndApplication, PasswordEncoder passwordEncoder, UserRepository userRepository) {
		this.shopitBackEndApplication = shopitBackEndApplication;
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	@GetMapping("/users")
	public String listAll(Model model) {
		List<User> listAllUsers = service.listAllUsers();
		List<Role> listAllRoles = service.listAllRoles();
		model.addAttribute("listAllUsers", listAllUsers);
		model.addAttribute("listAllRoles", listAllRoles);
		return "users";
	}

	@GetMapping("/users/new")
	public String createUser(Model model) {
		User user = new User();
		List<Role> listAllRoles = service.listAllRoles();
		model.addAttribute("user", user);
		model.addAttribute("listAllRoles", listAllRoles);
		model.addAttribute("pageTitle", "Create New User");
		model.addAttribute("fieldDisabled", false);
		return "user_form";
	}

	@PostMapping("/users/save")
	public String saveUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes, Model model) {

		if (service.isEmailUnique(user.getEmail())) {
			service.save(user);
			redirectAttributes.addFlashAttribute("message", "The User has been saved successfully !!");
			return "redirect:/users";
		} else {
			model.addAttribute("duplicateEmailmessage", "Email is already registered, use a different email !!");
			model.addAttribute("listAllRoles", service.listAllRoles());
			return "user_form"; // No redirect
		}
	}

	@GetMapping("/users/edit/{id}")
	public String getUserById(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		try {
			User user = service.getUser(id);
			model.addAttribute("user", user);
			model.addAttribute("listAllRoles", service.listAllRoles());
			model.addAttribute("pageTitle", "Edit User - " + user.getId());
			model.addAttribute("fieldDisabled", true);
			return "user_form_update";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("userNotFoundException", "User not found !!");
			return "redirect:/users";
		}
	}

	@PostMapping("/users/update")
	public String updateUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes, Model model) {
		/*
		 * 
		 * 
		 * pasword field will be remain as empty and values are not displayed in the
		 * field for security purpose, yet the user has two options either he can leave
		 * the field blank or he can change the password, the field does not remain
		 * required when it's in edit user mode.
		 *
		 * 
		 */

		User existingUser = service.getUser(user.getId());

		// Check if email is changed
		boolean emailChanged = !existingUser.getEmail().equals(user.getEmail());

		if (emailChanged && !service.isEmailUnique(user.getEmail())) {
			model.addAttribute("duplicateEmailmessage", "Email is already registered, use a different email !!");
			model.addAttribute("listAllRoles", service.listAllRoles());
			return "user_form"; // stay on same page
		}

		// Handle password update logic
		if (user.getPassword() == null || user.getPassword().isBlank()) {
			user.setPassword(existingUser.getPassword());
		} else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}

		// Save updated user
		service.updateAndSave(user); // this skips email uniqueness check
		redirectAttributes.addFlashAttribute("saveandUpdate", "User updated and saved successfully !!");
		return "redirect:/users";
	}

	@GetMapping("/users/delete/{id}")
	// http://localhost:8080/users/delete/6
	public String deleteUser(@PathVariable Integer id, RedirectAttributes redirectAttributes, Model model) {
		try {
			service.deleteUser(id);
			redirectAttributes.addFlashAttribute("userDeletedSuccessMessage", "User has been deleted successfully !!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			redirectAttributes.addFlashAttribute("userDeletedNotFoundMessage", "No User found !!");
		}
		return "redirect:/users";

	}
}
