package com.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ChangePasswordRepository {

	@Autowired
	JdbcTemplate template;
	
	// check for change password user
	public int checkPassword(String pass ,int registerid) {
		try {
			Integer uid = template.queryForObject("select registerid from registrationmaster where registerid=? and password=?", new Object[] {registerid,pass}, new RowMapper<Integer>() {
				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
			});
			return (uid>0)?uid:0;
		}catch(Exception e) {
			System.out.println("change repo error :"+e);
			return 0;
		}
	}
	
	
	// change password user
		public int changeUserPassword(final String pass ,final int registerid) {
			try {
				int v = template.update("update registrationmaster set password=? where registerid=?", new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, pass);
						ps.setInt(2, registerid);
					}
				});
				return (v>0) ? 1: 0;
			}catch(Exception e) {
				System.out.println("error :"+e);
				return 0;
			}
		}
		
	// forgot password check username
	public int checkUsername(String username) {
		try {
			Integer uid = template.queryForObject("select registerid from registrationmaster where username=?", new Object[] {username}, new RowMapper<Integer>() {
				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
			});
			return (uid>0)?uid:0;
		}catch(Exception e) {
			System.out.println("change repo error: "+e);
			return 0;
		}
	}
	
	// check email and username particular user
	public int checkEmail(String email, String username) {
		try {
			Integer uid = template.queryForObject("select registerid from registrationmaster where email=? and username=?", new Object[] {email,username}, new RowMapper<Integer>() {
				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {	
					return rs.getInt(1);	
				}
			});
			return (uid>0)?uid:0;
		}catch(Exception e) {
			System.out.println("error: "+e);
			return 0;
		}
	}
}
