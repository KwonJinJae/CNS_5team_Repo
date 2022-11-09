package com.lgUCamp.catchMe.Service;

import com.lgUCamp.catchMe.DTO.UserDTO;
import com.lgUCamp.catchMe.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserDTO userDTO = userMapper.getUserInfo(userId);
        System.out.println(passwordEncoder.encode(userDTO.getUser_pass()));
        if (userDTO == null){
            throw new UsernameNotFoundException("User not authorized.");
        }

        System.out.println("**************Found user***************");
        System.out.println("id : " + userDTO.getUsername());
        System.out.println(userDTO);
        System.out.println(userDTO.getUser_pass());
        System.out.println(userDTO.getAuthority_name());
        System.out.println("**************end print***************");
        return userDTO;
    }
}
