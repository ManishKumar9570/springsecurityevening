package com.seleniumexpress.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {
	
	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;

	// Principal means username
	@GetMapping("/") // don't secure this
	public String getHelloWorld(Principal principal,Authentication auth,Model model) { 
		if(principal!=null) {
		//fetching the userName
		String userName = principal.getName();
		System.out.println("Logged in user is "+userName);
		//fetching the roles/authorities
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		System.out.println("Authorities are "+authorities);
		
		model.addAttribute("userName", userName);
		model.addAttribute("roles", authorities);
		
		return "home-page";
		}else {
			System.out.println("Welcome in Hello world");
			return "redirect:/signup";
		}
	}
	
	@GetMapping("/trainer") //secure it
	public String showTrainerDashboard() {
		return "trainer-dashboard";
	}
	@GetMapping("/coder") //secure it
	public String showCoderDashboard() {
		return "coder-dashboard";
	}
	@GetMapping("/accessDenied") //secure it
	public String error() {
		return "access-denied";
	}
	
	@GetMapping("/delete") //secure it
	public String deleteAccount(@RequestParam("username") String userName) {
		jdbcUserDetailsManager.deleteUser(userName);
		System.out.println("User delted : "+userName);
		return "redirect:/myCustomLogin";
	}
}
