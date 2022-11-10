package com.lgUCamp.catchMe.Controller;

import com.lgUCamp.catchMe.DTO.UserDTO;
import com.lgUCamp.catchMe.DTO.UserDetail;
import com.lgUCamp.catchMe.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserService userService;

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
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();  //userDetail 객체를 가져옴
        model.addAttribute("info", userDetail.getUserId() +"님");      //유저 아이디

        return "accessInfo/user_access";
    }

    @RequestMapping("/login/findID")
    public String findId() {

        return "accessInfo/findID";
    }

    @RequestMapping("/login/findPwd")
    public String findPwd() {

        return "accessInfo/findPwd";
    }

    @GetMapping("/signUp")
    public String signUpForm(UserDTO userDTO) {
        return "accessInfo/signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            return "accessInfo/signUp.html";
        }
        userService.joinUser(userDTO);
        return "redirect:/login";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        String html = "";

        for(UserDTO user : userService.selectAll()) {
            html += "no: " + user.getUserNo() + ", id: " + user.getUserId() + ", name: " + user.getUserName();
        }
        return html;
    }
}
