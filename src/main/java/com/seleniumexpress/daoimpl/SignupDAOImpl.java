package com.seleniumexpress.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.seleniumexpress.dao.SignupDAO;
import com.seleniumexpress.dto.SignupDTO;

@Repository
public class SignupDAOImpl implements SignupDAO {

	@Autowired
	private JdbcTemplate jdbctemplate;
	
	@Override
	public void saveUser(SignupDTO signupDTO) {
		String sql="insert into users values(?,?,?)";
		String sql2="insert into authorities values(?,?)";
		jdbctemplate.update(sql,signupDTO.getUsername(),signupDTO.getPassword(),1);
		jdbctemplate.update(sql2,signupDTO.getUsername(),"USER");
	}

}
