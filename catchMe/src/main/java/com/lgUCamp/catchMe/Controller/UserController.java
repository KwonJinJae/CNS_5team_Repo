package com.lgUCamp.catchMe.Controller;

import com.lgUCamp.catchMe.DTO.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String loginPage() {
        return "accessInfo/loginPage";
    }

    @GetMapping("/access_denied")
    public String accessDenied() {
        return "accessInfo/access_denied";
    }

    @GetMapping("/user_access")
    public String userAccess(Model model, Authentication authentication) {
        //Authentication 객체를 통해 유저 정보를 가져올 수 있다.
        UserDTO userDTO = (UserDTO) authentication.getPrincipal();  //userDetail 객체를 가져옴
        model.addAttribute("info", userDTO.getUser_id() +"의 "+ userDTO.getUser_nickname()+ "님");      //유저 아이디

        return "user_access";
    }

    @RequestMapping("/login/findID")
    public String findId() {

        return "accessInfo/findID";
    }

    @RequestMapping("/login/findPwd")
    public String findPwd() {

        return "accessInfo/findPwd";
    }

    @RequestMapping("/signUp")
    public String signUp() {

        return "accessInfo/signUp";
    }
}
