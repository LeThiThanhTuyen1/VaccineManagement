package com.example.vaccineapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.vaccineapp.models.User;
import com.example.vaccineapp.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/manage")
	public String accountList(Model model) {
	    model.addAttribute("users", userService.findAllUsers()); // Thêm danh sách người dùng vào model
		return "user-account-manage";
	}

	@PostMapping("/enable-user")
	public String enableUser(@RequestParam("id") Long userId) {
	    userService.enableUserAccount(userId);
	    return "redirect:/users/manage"; // Chuyển hướng về danh sách người dùng sau khi kích hoạt lại tài khoản
	}

	@PostMapping("/disable-user")
	public String disableUser(@RequestParam("id") Long userId) {
	    userService.disableUserAccount(userId);
	    return "redirect:/users/manage"; // Chuyển hướng về danh sách người dùng sau khi tắt tài khoản
	}
	
	 @GetMapping("/users/search")
	    public String searchUsers(@RequestParam("username") String username, Model model) {
	        List<User> users = userService.findUsersByUsername(username);
	        model.addAttribute("users", users);
	        return "user-account-manage"; // Tên view
	    }
}
