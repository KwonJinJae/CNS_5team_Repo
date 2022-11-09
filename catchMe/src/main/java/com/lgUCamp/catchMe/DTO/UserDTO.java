package com.lgUCamp.catchMe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
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
}
