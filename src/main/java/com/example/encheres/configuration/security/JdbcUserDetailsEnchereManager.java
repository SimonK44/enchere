package com.example.encheres.configuration.security;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

public class JdbcUserDetailsEnchereManager extends JdbcUserDetailsManager {
	
	public JdbcUserDetailsEnchereManager(DataSource dataSource) {
		setDataSource(dataSource);
	}
 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    RowMapper<UserDetails> mapper = (rs, rowNum) -> {
	        String username1 = rs.getString(1);
	        String password = rs.getString(2);
	        boolean enabled = rs.getBoolean(3);
	        return new User(username1, password, enabled, true, true, true, AuthorityUtils.NO_AUTHORITIES);
	    };
	    List<UserDetails> users = getJdbcTemplate().query(this.getUsersByUsernameQuery(), mapper, username, username);
	    if (users.isEmpty()) {
	        throw new UsernameNotFoundException("User not found with username or email: " + username);
	    }
	    return users.get(0);
	}	

 
}


