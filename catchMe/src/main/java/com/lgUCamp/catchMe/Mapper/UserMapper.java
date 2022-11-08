package com.lgUCamp.catchMe.Mapper;

import com.lgUCamp.catchMe.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserDTO getUserInfo(String user_id);

    void saveUser(UserDTO userDTO);
}
