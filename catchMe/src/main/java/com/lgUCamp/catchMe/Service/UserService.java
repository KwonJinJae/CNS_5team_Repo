package com.lgUCamp.catchMe.Service;

import com.lgUCamp.catchMe.DTO.AuthDTO;
import com.lgUCamp.catchMe.DTO.UserDTO;
import com.lgUCamp.catchMe.DTO.UserDetail;
import com.lgUCamp.catchMe.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserDTO userDTO = userMapper.getUserInfo(userId);
        ArrayList<AuthDTO> userAuths = userMapper.getUserAuth(userDTO.getUser_no());

        ArrayList<String> userAuthName = new ArrayList<>();

        for(AuthDTO userAuth : userAuths) {
            userAuthName.add(userAuth.getAuthority_name());
        }

        UserDetail user = new UserDetail(userDTO.getUser_id(), userDTO.getUser_pass(), userAuthName);

        if (user == null){
            throw new UsernameNotFoundException("User not authorized.");
        }

        return user;
    }
}
