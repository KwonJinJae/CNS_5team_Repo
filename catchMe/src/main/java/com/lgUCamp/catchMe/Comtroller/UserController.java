package com.lgUCamp.catchMe.Comtroller;

import com.lgUCamp.catchMe.DTO.UserDTO;
import com.lgUCamp.catchMe.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/test")
    @ResponseBody
    public String test(String user_id) {
        userService.test(user_id);
        return "test";
    }

    @RequestMapping("/login")
    public String login() {
        return "accessInfo/loginPage";
    }

    @RequestMapping("/signUp")
    public String signUp() {
        return "accessInfo/signUp";
    }

    @GetMapping("/access_denied")
    public String accessDenied() {
        return "login/access_denied";
    }

    @PostMapping("/signUp")
    public String signUp(UserDTO userDTO) {
        userService.joinUser(userDTO);
        return "redirect:/login";
    }

    @GetMapping("/user_access")
    public String userAccess(Model model, Authentication authentication) {
        //Authentication 객체를 통해 유저 정보를 가져올 수 있다.
        UserDTO userDTO = (UserDTO) authentication.getPrincipal();  //userDetail 객체를 가져옴
        model.addAttribute("info", userDTO.getUser_id() +"의 "+ userDTO.getUser_nickname()+ "님");      //유저 아이디

        return "user_access";
    }
}
