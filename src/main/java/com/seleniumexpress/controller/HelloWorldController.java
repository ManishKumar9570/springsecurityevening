package com.seleniumexpress.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

	// Principal means username
	@GetMapping("/") // don't secure this
	public String getHelloWorld(Principal principal,Authentication auth,Model model) { 
		//fetching the userName
		String userName = principal.getName();
		System.out.println("Logged in user is "+userName);
		//fetching the roles/authorities
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		System.out.println("Authorities are "+authorities);
		
		model.addAttribute("userName", userName);
		model.addAttribute("roles", authorities);
		
		
		return "home-page";
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
}
