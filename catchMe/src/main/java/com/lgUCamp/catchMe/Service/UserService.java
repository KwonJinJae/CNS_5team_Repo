package com.lgUCamp.catchMe.Service;

import com.lgUCamp.catchMe.DTO.Admin;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public void joinUser(UserDTO userDTO){
        userDTO.setUserPass(passwordEncoder.encode(userDTO.getUserPass()));
        userMapper.joinUser(userDTO);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserDTO userDTO = userMapper.getUserInfo(userId);
        Admin adminDTO = userMapper.getAdminInfo(userId);
        UserDetail user = new UserDetail();

        ArrayList<AuthDTO> Auths = userMapper.getUserAuth(userDTO != null ? userDTO.getUserNo() : 0, adminDTO != null ? adminDTO.getAdminNo() : 0);

        ArrayList<String> AuthName = new ArrayList<>();

        for(AuthDTO Auth : Auths) {
            AuthName.add(Auth.getAuthorityName());
        }

        if(userDTO != null) {
            user = new UserDetail(userDTO.getUserId(), userDTO.getUserPass(), AuthName);
            System.out.println("userDTO의 user : "+user);
        }

        if(adminDTO != null) {
            user = new UserDetail(adminDTO.getAdminId(), adminDTO.getAdminPass(), AuthName);
            System.out.println("adminDTO의 admin: "+user);
        }

        if (Auths == null){
            throw new UsernameNotFoundException("User not authorized.");
        }

        return user;
    }

    public ArrayList<UserDTO> selectAll() {
        return userMapper.selectAll();
    }
}
