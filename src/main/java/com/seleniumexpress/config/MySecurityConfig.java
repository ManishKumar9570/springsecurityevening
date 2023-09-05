package com.seleniumexpress.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// this class is going to help you to create the spring security filter chain 
@EnableWebSecurity(debug=true) // creating a chain of filters called SpringSecurityFilterChain using @EnableWebSecurity annotation
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	// I want to create some details for an user
	// username, password, roles
	// manish, mani123, admin
	
	@Autowired
	private PasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//now load the user info from database
		// username, password and role is mandatory to load the user from db to login into system
		// here using spring security default user schema to store the data in database then we don't need to write code for it just simply we have to follow that default schema to automatically store and load user details in/from database
		//https://docs.spring.io/spring-security/site/docs/4.2.x/reference/html/appendix-schema.html
		
		
		//here saving and loading the user from server memory
	/*
		auth
		.inMemoryAuthentication()
		.withUser("manish")
		//.password("mani123")//mani123 // here using noop id for NoOpPasswordEncoder encoder. With the help of specific encoder id we can directly use it like in this line
		//.password("{noop}mani123")//mani123 // here using noop id for NoOpPasswordEncoder encoder. With the help of specific encoder id we can directly use it like in this line
		.password("{bcrypt}$2a$10$kLj4klrn4Bh7tT1nLKEDZ.748ndvI9xxYrY/XUU5vWQfy68XfU6qm")//mani123 // here using id for bcryptpasswordEncoder encoder. With the help of specific encoder id we can directly use it like in this line i.e we don't need to create bean for specific passwordEnocder to use as id for that specific passwordEncoder 
		//.password("$2a$10$kLj4klrn4Bh7tT1nLKEDZ.748ndvI9xxYrY/XUU5vWQfy68XfU6qm")//mani123 // here using id for bcryptpasswordEncoder encoder. With the help of specific encoder id we can directly use it like in this line
		//.password(bcryptPasswordEncoder.encode("mani123"))//mani123
		.roles("admin")
		//.and().withUser("Rohit").password("$2a$10$2SyviklNH5Le9tkgRk5dYe7bQbv20Ar6Z0Kf1xQFjoRIraQq2PUnC")//r123 //when you specify the BcryptPasswordEncoder and created bean by spring IOC then no need to pass as bcrypt id like above to authenticate as BcryptPasswordEncoder
		//.roles("user")
		.and()
		.withUser("kartik").password("{bcrypt}$2a$12$VIn6aiqFweTNXWlKkSafXOrbpkY6DD1/JvtqztJ1q.Z6nYqSdx/9i").roles("user"); //k123
	*/
		//System.out.println("mani123 encoded password is "+bcryptPasswordEncoder.encode("mani123"));
		
		// here saving and loading the user from mysql database
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(bcryptPasswordEncoder);
	}
	
/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.and()
		.httpBasic();
	}
*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/hello").authenticated()
		.antMatchers("/bye").authenticated()
		.antMatchers("/helloWorld").permitAll()
		.and()
		.formLogin().loginPage("/myCustomLogin").loginProcessingUrl("/process-login") //by using loginPage here it will show the our custom login page instead of default spring login page
		.and()
		.httpBasic()
		.and().logout();
	}

	
	
}
