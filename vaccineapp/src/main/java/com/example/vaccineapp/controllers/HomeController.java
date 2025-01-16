package com.example.vaccineapp.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
	
	@GetMapping("/login")
	public String showLoginForm() {
	    System.out.println("Rendering login page");
	    return "login";
	}
	
	@GetMapping("/home")
	public String Home(Model model) {
		return "home"; 
	}
	
	@GetMapping("/logout")
	public String Logout(HttpServletRequest request, HttpServletResponse response) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
	    logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
	    return "redirect:/login";
	}
}
