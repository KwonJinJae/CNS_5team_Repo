package com.lgUCamp.catchMe.Service;

import com.lgUCamp.catchMe.DTO.Admin;
import com.lgUCamp.catchMe.DTO.AuthDTO;
import com.lgUCamp.catchMe.DTO.UserDTO;
import com.lgUCamp.catchMe.DTO.UserDetail;
import com.lgUCamp.catchMe.Entity.UserEntity;
import com.lgUCamp.catchMe.Mapper.UserMapper;
import com.lgUCamp.catchMe.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public List<UserEntity> findAll() {
        List<UserEntity> users = new ArrayList<>();
        userRepository.findAll().forEach(e -> users.add(e));
        return users;
    }

    public boolean checkUserIdDuplication(String userId) {
        boolean userIdDuplicate = userRepository.existsByUserId(userId);
        System.out.println(userIdDuplicate);
        return userIdDuplicate;
    }

    public boolean checkUserNicknameDuplication(String userNickname) {
        boolean nicknameDuplicate = userRepository.existsByUserNickname(userNickname);
        System.out.println(userNickname);
        System.out.println(nicknameDuplicate);
        return nicknameDuplicate;

    }

    public boolean checkUserPhoneDuplication(String userPhone) {
        boolean phoneDuplicate = userRepository.existsByUserPhone(userPhone);
        System.out.println(phoneDuplicate);
        return phoneDuplicate;
    }

    public boolean checkUserPassValidation(String userPass, String userPass1) {
        boolean result = false;
        if(userPass.equals(userPass1)) {
            result = true;
        }
        return result;
    }


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
        }

        if(adminDTO != null) {
            user = new UserDetail(adminDTO.getAdminId(), adminDTO.getAdminPass(), AuthName);
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
