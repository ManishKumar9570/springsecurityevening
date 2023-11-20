package com.seleniumexpress.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seleniumexpress.dao.ChangePasswordDTO;

@Controller
public class HelloWorldController {
	
	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	// Principal means username
	@GetMapping("/home") // don't secure this
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
	@GetMapping("/changePassword") //secure it
	public String changePassword(Model model) {
		model.addAttribute("change_Password", new ChangePasswordDTO());
		return "change_password";
	}

	@PostMapping("/save-password") //secure it
	public String savePassword(Principal principal, ChangePasswordDTO changePasswordDTO) {
		System.out.println(changePasswordDTO);
		UserDetails user = jdbcUserDetailsManager.loadUserByUsername(principal.getName());
		//check old password is correct
		boolean matches = passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword());
		
		//check whether the old password and new password matches
		if(!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
			return "redirect:/changePassword?invalidConfirmPassword";
		}
		//write some logic to save the new password in database
		if(matches) {
		String encodedPassword = passwordEncoder.encode(changePasswordDTO.getConfirmPassword());
		jdbcUserDetailsManager.changePassword(changePasswordDTO.getOldPassword(), encodedPassword);
		System.out.println("password Changed");
		return "redirect:/myCustomLogin";
		}
		return "redirect:/changePassword?invalidPassword";
	}
}
