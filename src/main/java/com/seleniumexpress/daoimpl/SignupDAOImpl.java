package com.seleniumexpress.daoimpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.seleniumexpress.dao.SignupDAO;
import com.seleniumexpress.dto.SignupDTO;

@Repository
public class SignupDAOImpl implements SignupDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void saveUser(SignupDTO signupDTO) {
		String sql="insert into users values(?,?,?)";
		String sql2="insert into authorities values(?,?)";
		//jdbcTemplate.update(sql,signupDTO.getUsername(),signupDTO.getPassword(),1);
		//jdbcTemplate.update(sql2,signupDTO.getUsername(),"USER");
		//another way to update data using update method of jdbcTepmplace object
		jdbcTemplate.update(sql,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, signupDTO.getUsername());
				ps.setString(2, signupDTO.getPassword());
				ps.setInt(3, 1);
			}
		});
		
		jdbcTemplate.update(sql2,ps -> {
			ps.setString(1, signupDTO.getUsername());
			ps.setString(2, "USER");
		});
	}

}
