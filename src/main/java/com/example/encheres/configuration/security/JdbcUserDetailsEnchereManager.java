package com.example.encheres.configuration.security;

import java.util.List;

import javax.sql.DataSource;
 
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
 
public class JdbcUserDetailsEnchereManager extends JdbcUserDetailsManager{
 
	public JdbcUserDetailsEnchereManager(DataSource dataSource) {
		super(dataSource);
	}
 
	@Override
	protected List<UserDetails> loadUsersByUsername(String username) {
	    RowMapper<UserDetails> mapper = (rs, rowNum) -> {
	        String username1 = rs.getString(1);
	        String password = rs.getString(2);
	        boolean enabled = rs.getBoolean(3);
	        
	        return new User(username1, password, enabled, true, true, true, AuthorityUtils.NO_AUTHORITIES);	        
	    };
	    return getJdbcTemplate().query(this.getUsersByUsernameQuery(), mapper, username, username);
	}
	
	protected List<GrantedAuthority> loadUserAuthorities(String username) {
		return getJdbcTemplate().query(this.getAuthoritiesByUsernameQuery(), new String[] { username, username }, (rs, rowNum) -> {
			String roleName = this.getRolePrefix() + rs.getString(2);			
			return new SimpleGrantedAuthority(roleName);
		});
	}

 
}


