package com.lgUCamp.catchMe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements UserDetails {

    private int user_no;
    private String user_name;
    private String user_birth;
    private String user_mail;
    private String user_phone;
    private String user_nickname;
    private String user_id;
    private String user_pass;
    private String join_date;
    private String user_img;
    private String user_img_path;
    private String authority_name;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority(authority_name));
        System.out.println(authList);
        return authList;
    }

    @Override
    public String getPassword() {
        return this.user_pass;
    }

    @Override
    public String getUsername() {
        return this.user_id;
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
}
