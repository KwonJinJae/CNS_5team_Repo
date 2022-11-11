package com.lgUCamp.catchMe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int userNo;
    private String userName;
    private String userBirth;
    private String userMail;
    private String userPhone;
    private String userNickname;
    private String userId;
    private String userPass;
    private String joinDate;
    private String userImg;
    private String userImgPath;
}
