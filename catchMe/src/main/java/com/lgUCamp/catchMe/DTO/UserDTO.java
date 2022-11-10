package com.lgUCamp.catchMe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotNull
    private int userNo;
    @NotEmpty(message = "필수 정보입니다.")
    private String userName;
    @NotEmpty(message = "필수 정보입니다.")
    @Pattern(regexp = "\\d{6}-[1-4]", message = "######-#형식으로 입력해주세요.")
    private String userBirth;
    @NotNull
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String userMail;
    @NotEmpty(message = "필수 정보입니다.")
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "###-####-####형식으로 입력해주세요.")
    private String userPhone;
    @NotEmpty(message = "필수 정보입니다.")
    private String userNickname;
    @NotEmpty(message = "필수 정보입니다.")
    private String userId;
    @NotEmpty(message = "필수 정보입니다.")
    @Pattern(regexp = "^.*(?=^.{4,16}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", message = "숫자, 문자, 특수문자 포함 4~16자리 이내로 입력해주세요.")
    private String userPass;
    private String joinDate;
    private String userImg;
    private String userImgPath;
}
