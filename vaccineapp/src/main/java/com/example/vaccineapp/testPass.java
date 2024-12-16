package com.example.vaccineapp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class testPass {
	public static void main(String[] args) {
//		System.out.print(new BCryptPasswordEncoder().encode("admin123"));
		System.out.print(new BCryptPasswordEncoder().encode("manager123"));
	}
}
