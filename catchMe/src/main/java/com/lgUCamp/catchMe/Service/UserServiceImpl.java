package com.lgUCamp.catchMe.Service;

import com.lgUCamp.catchMe.DTO.UserDTO;
import com.lgUCamp.catchMe.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService{

    @Autowired
    UserMapper userMapper;

    public void test(String user_id) {
        System.out.println(userMapper.getUserInfo(user_id));

    }

    @Transactional
    public void joinUser(UserDTO userDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDTO.setUser_pass(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setAuthority_code("ROLE_MEMBER");
        userMapper.saveUser(userDTO);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserDTO userDTO = userMapper.getUserInfo(userId);
        if (userDTO == null){
            throw new UsernameNotFoundException("User not authorized.");
        }
        return userDTO;
    }
}
