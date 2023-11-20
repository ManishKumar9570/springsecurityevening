package com.seleniumexpress.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
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
	private DataSource dataSource;
	
	@Autowired
	private SignupDAO signupDAO;
	
	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;

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
		
		//creating UserDetails object
		UserDetails userDetails = User.withUsername(signupDTO.getUsername()).password(encodedPassword).authorities("CODER").build();
		
		//creating JdbcUserDetailsManager object and calling createUser() method to store user details in db 
		//here creating the JdbcUserDetailsManager object and setting the dataSource using constructor call
		//commenting below line to use autowiring feature
		//JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
		//setting our own datasource to connect with db
		//userDetailsManager.setDataSource(dataSource);
		//userDetailsManager.createUser(userDetails);
		jdbcUserDetailsManager.createUser(userDetails);
		
		//save the dto : do db call to save in database
		//signupDAO.saveUser(signupDTO);
		return "redirect:/myCustomLogin";
	}
}
