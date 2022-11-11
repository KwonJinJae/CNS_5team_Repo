package com.lgUCamp.catchMe.Mapper;

import com.lgUCamp.catchMe.DTO.Admin;
import com.lgUCamp.catchMe.DTO.AuthDTO;
import com.lgUCamp.catchMe.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface UserMapper {
    ArrayList<UserDTO> selectAll();

    UserDTO getUserInfo(String userId);

    Admin getAdminInfo(String adminId);

    ArrayList<AuthDTO> getUserAuth(int user_no, int admin_no);

    void joinUser(UserDTO userDTO);
}