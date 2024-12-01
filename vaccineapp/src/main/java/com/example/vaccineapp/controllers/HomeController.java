package com.example.vaccineapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/login")
	public String showLoginForm() {
	    System.out.println("Rendering login page");
	    return "login";
	}
	
	@GetMapping("/")
	public String Home() {
	    System.out.println("Rendering home page");
	    return "home"; 
	}
}
