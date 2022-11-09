package com.lgUCamp.catchMe.Challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;




    @RequestMapping("Challenge/Challenge_main")
    public String ChallengeMain(){
        return "Challenge/Challenge_main";
    }

    @RequestMapping("Challenge/Challenge_certify")
    public String ChallengeCertify(Model model){
        Challenge chg1 = challengeService.selectOne(1);
        System.out.println(chg1);
        return "Challenge/Challenge_certify";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
       challengeService.selectAll();
        return "";
    }

//    @RequestMapping("/Challenge/challenge_certify")
//    public String ChallengeCertify(){
//
//        return "Challenge_certify";
//    }
//    @RequestMapping("/Challenge/certify_comment")
//    public String ChallengeCertifyComment(){
//
//        return "Challenge_certify_comment";
//    }
//    @RequestMapping("/Challenge/certifyCommentAdd")
//    public String ChallengeCertifyCommentAdd(){
//        return "Challenge_certify_comment_add";
//    }

}
