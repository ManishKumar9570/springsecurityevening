package com.seleniumexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.seleniumexpress.dao.SignupDAO;
import com.seleniumexpress.dto.SignupDTO;

@Controller
public class LoginController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SignupDAO signupDAO;

	@GetMapping("/myCustomLogin") 
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup") 
	public String signup(@ModelAttribute("signupdto")SignupDTO  signupDTO) {
		return "signup";
	}
	
	@PostMapping("/process-signup") 
	public String processSignup(SignupDTO  signupDTO) {
		System.out.println(signupDTO);
		String encodedPassword = passwordEncoder.encode(signupDTO.getPassword());
		signupDTO.setPassword(encodedPassword);
		System.out.println(signupDTO);
		//save the dto : do db call to save in database
		signupDAO.saveUser(signupDTO);
		return "redirect:/myCustomLogin";
	}
}
