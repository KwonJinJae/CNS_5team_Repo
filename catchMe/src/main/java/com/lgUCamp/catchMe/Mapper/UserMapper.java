package com.lgUCamp.catchMe.Mapper;

import com.lgUCamp.catchMe.DTO.AuthDTO;
import com.lgUCamp.catchMe.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface UserMapper {

    UserDTO getUserInfo(String userId);

    ArrayList<AuthDTO> getUserAuth(int user_no);

    void saveUser(UserDTO userDTO);
}