package com.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.model.LoginModel;

@Repository
public class UserLoginRepository {

	@Autowired
	JdbcTemplate template;
	
	/*check user login in database */
	public int checkUserLogin(final LoginModel login) {
	    List<Integer> registerId = template.query("select registerid from registrationmaster where username = ? and password = ?",
	        new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	                ps.setString(1, login.getUsername());
	                ps.setString(2, login.getPassword());
	            }
	        },new RowMapper<Integer>() {
	            @Override
	            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
	                return rs.getInt("registerid");
	            }
	        }
	    );
	    return registerId.size()>0 ?  registerId.get(0) : -1;
	}

	/* when user login then login data added in database table*/
	public boolean isAddUserLogin(final LoginModel login) {
		int v=template.update("insert into loginmaster values('0',?,?,(select curdate()),(select curtime()),?)", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException{
				ps.setString(1, login.getUsername());
				ps.setString(2, login.getPassword());
				ps.setInt(3, login.getLoginid());	
			}
		});
		return (v>0) ?true:false;
	}
}
