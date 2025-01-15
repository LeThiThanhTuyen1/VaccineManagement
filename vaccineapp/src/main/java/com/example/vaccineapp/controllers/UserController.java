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
		model.addAttribute("users", userService.findAllUsers());
		return "user/list";
	}

	@PostMapping("/enable-user")
	public String enableUser(@RequestParam("id") Long userId) {
		userService.enableUserAccount(userId);
		return "redirect:/users/manage";
	}

	@PostMapping("/disable-user")
	public String disableUser(@RequestParam("id") Long userId) {
		userService.disableUserAccount(userId);
		return "redirect:/users/manage";
	}

	@GetMapping("/search")
	public String searchUsers(@RequestParam("username") String username, Model model) {
		model.addAttribute("username", username);
		try {
			List<User> users = userService.findUsersByUsername(username);
			if (users.isEmpty()) {
				model.addAttribute("error", "Không tìm thấy tài khoản nào.");
			}
			model.addAttribute("users", users);
		} catch (Exception e) {
			model.addAttribute("error", "Có lỗi xảy ra trong quá trình tìm kiếm.");
		}
		return "redirect:/users/manage";
	}
	
	@GetMapping("/create")
    public String showCreateUserPage(Model model) {
        return "user/add"; 
    }

    @PostMapping("/save")
    public String saveUser(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("role") String role, 
                           Model model) {
        try {
            userService.createUser(username, password, role); 
            model.addAttribute("message", "Tạo tài khoản thành công.");
        } catch (Exception e) {
            model.addAttribute("error", "Có lỗi xảy ra khi tạo tài khoản.");
        }
        return "redirect:/users/manage"; 
    }
}
