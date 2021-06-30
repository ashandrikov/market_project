package com.shandrikov.market.market_project.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class MyUserDetails implements UserDetails, GrantedAuthority {

    private User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isAdmin() {
        return user.getRole().equals("ADMIN");
    }

    public String getRoleNavBar(){
        return user.getRole().equals("ADMIN")
                ?"Admin"
                :"User";
    }

    @Override
    public String getAuthority() {
        return user.getRole();
    }

    public boolean isEnables(){
        return user.isEnabled();
    }

    public String getRole(){
        return user.getRole();
    }

    public void setUsername (String username){
        this.user.setUsername(username);
    }

    public void setEnabled (boolean enabled){
        this.user.setEnabled(enabled);
    }

    public void setRole (String role){
        this.user.setRole(role);
    }

    public void setPassword (String password){
        this.user.setPassword(password);
    }

    public User getUser(){
        return this.user;
    }
}
