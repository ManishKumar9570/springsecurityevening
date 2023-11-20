package com.seleniumexpress.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com")
public class MyAppConfig {

	@Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
	
	@Bean
	PasswordEncoder passwordEncoder(){
		//return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}
	
	
	//how can we conect to db : Datsource
	//here we are configuring datasource/database connection

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("password");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/springsecurityevening");
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		
		return driverManagerDataSource;
	}
	
	@Bean
	public JdbcTemplate  jdbctemplate() {
		JdbcTemplate jdbcTemplate= new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		
		return jdbcTemplate;
		
	}
	
	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager() {
		return new JdbcUserDetailsManager(dataSource());
		
	}
	
}