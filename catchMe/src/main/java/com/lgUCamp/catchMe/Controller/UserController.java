package com.lgUCamp.catchMe.Controller;

import com.lgUCamp.catchMe.DTO.UserDTO;
import com.lgUCamp.catchMe.DTO.UserDetail;
import com.lgUCamp.catchMe.Entity.UserEntity;
import com.lgUCamp.catchMe.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/login/findIdForm")
    public String findIdForm() {
        return "accessInfo/findIdForm";
    }

    @PostMapping("/login/findId")
    public String findIdForm(String userName, String userPhone, Model model) {
        UserDTO user = userService.findUserInfo(userPhone);

        if(user.getUserName().equals(userName)) {
            model.addAttribute("userId", user.getUserId());
            return "accessInfo/findId";
        }

        return "redirect:/login/findIdForm";
    }

    @GetMapping("/login/findPwdForm")
    public String findPwdForm() {
        return "accessInfo/findPwdForm";
    }

    @PostMapping("/login/findPwd")
    public String findPwd(String userId, String userPhone, Model model) {
        UserDTO user = userService.findUserInfo(userPhone);

        if(user.getUserId().equals(userId)) {
            return "accessInfo/findPwd";
        }

        return "redirect:/login/findPwdForm";
    }

    @GetMapping("/signUp")
    public String signUpForm(UserDTO userDTO) {
        return "accessInfo/signUp";
    }

    @PostMapping("/signUpProc")
    public String signUp(@Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("userDTO", userDTO);

            if(userService.checkUserIdDuplication(userDTO.getUserId())) {
                model.addAttribute("errorId", "아이디가 이미 사용되고 있습니다.");
            }

            if(userService.checkUserNicknameDuplication(userDTO.getUserNickname())) {
                model.addAttribute("errorNickname", "닉네임이 이미 사용되고 있습니다.");
            }

            if(userService.checkUserPhoneDuplication(userDTO.getUserPhone())) {
                model.addAttribute("errorPhone", "휴대전화가 이미 사용되고 있습니다.");
            }

            return "accessInfo/signUp";
        }

        userService.joinUser(userDTO);
        return "redirect:/login";
    }

    @GetMapping("/test123")
    public ResponseEntity<List<UserEntity>> getAllmembers() {
        List<UserEntity> member = userService.findAll();
        return new ResponseEntity<List<UserEntity>>(member, HttpStatus.OK);
    }
}
