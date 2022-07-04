package com.bargunyo.alfonso.DiceGame8.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUser implements UserDetails {

	
	
	    private String user;
	    private String password;
	    private String token;
	    private Collection<? extends GrantedAuthority> authorities;
	    
	    


	    public LoginUser() {
			super();
		}
		public LoginUser(String user, String password, String token,
				Collection<? extends GrantedAuthority> authorities) {
			super();
			this.user = user;
			this.password = password;
			this.token = token;
			this.authorities = authorities;
		}
		@Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        // TODO Auto-generated method stub
	        return authorities;
	    }
	    @Override
	    public String getUsername() {
	        // TODO Auto-generated method stub
	        return user;
	    }
	    @Override
	    public boolean isAccountNonExpired() {
	        // TODO Auto-generated method stub
	        return false;
	    }
	    @Override
	    public boolean isAccountNonLocked() {
	        // TODO Auto-generated method stub
	        return false;
	    }
	    @Override
	    public boolean isCredentialsNonExpired() {
	        // TODO Auto-generated method stub
	        return false;
	    }
	    @Override
	    public boolean isEnabled() {
	        // TODO Auto-generated method stub
	        return false;
	    }
		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return null;
		}
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
			this.authorities = authorities;
		}
		
		
}
