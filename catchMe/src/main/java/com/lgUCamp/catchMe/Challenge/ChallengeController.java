package com.lgUCamp.catchMe.Challenge;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;

    @RequestMapping("Challenge/Challenge_main")
    public String ChallengeMain(Model model){
      ArrayList<Challenge> chNotices = challengeService.selectNoticeThree();
        model.addAttribute("chNotices",chNotices);
        return "Challenge/Challenge_main";
    }



    @RequestMapping("Challenge/Challenge_certify")
    public String ChallengeCertify(Model model){
        ArrayList<ChallengeCertify> chCertifys = challengeService.selectCertifyAll();
        System.out.println("============================" + chCertifys);
        model.addAttribute("chCertifys",chCertifys);
        return "Challenge/Challenge_certify";
    }


    @RequestMapping("/Challenge/Challenge_certify_writeform")
    public String ChallengeCertifyCommentwriteform(Model model, HttpServletRequest request){
  /* 화면에 작성자명을 보여주기 위해 세션에서 로그인 정보를 받아
           관리자 테이블의 직무와 관리자번호 정보를 불러온다. */
        // 현재 날짜 구하기
        LocalDate now = LocalDate.now();

        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // 포맷 적용
        String today = now.format(formatter);

        model.addAttribute("today", today);
        return "Challenge/Challenge_certify_writeform";
    }
    @RequestMapping("/Challenge/Challenge_certify_writeform/run")
    public String ChallengeCertifyCommentwriteformRun (HttpServletRequest request, ChallengeCertify challengeCertify){

//        challengeCertify.setUserNo(1);
//        System.out.println(challengeCertify);

        System.out.println("안녕하세요" + challengeCertify);
        challengeService.insertCertify(challengeCertify);
        return "redirect:/Challenge/Challenge_certify";
    }

    @RequestMapping("Challenge/Challenge_certify_read/{cProofNo}")
    public String Challenge_certify_read(@PathVariable int cProofNo, Model model) {

        ChallengeCertify chCertify= challengeService.selectCertifyOne(cProofNo);
        System.out.println(chCertify);

        model.addAttribute("chCertify", chCertify);

        return  "Challenge/Challenge_certify_read";
    };

    @RequestMapping("Challenge/delete/{cProofNo}")
    public String deleteCertify(Model model,@PathVariable int cProofNo){
        challengeService.deleteCertify(cProofNo);
        return "redirect:/Challenge/Challenge_certify";

    }

    @RequestMapping("Challenge/Certifyupdate/{cProofNo}")
    public String Certifyupdate(Model model,@PathVariable int cProofNo){
        challengeService.updateCertify(cProofNo);

        return "Challenge/Challenge_certify_updateform";

    }




    @RequestMapping("/Challenge/Challenge_certify_comment")
    public String ChallengeCertifyComment(){

        return "Challenge/Challenge_certify_comment";
    }

    @RequestMapping("/Challenge/certify_comment2")
    public String ChallengeCertifyComment2(){

        return "Challenge_certify_comment2";
    }

    //      chNotices.get()
//        ArrayList<Challenge> chNotices = new ArrayList<>(3);
//        //for(int i=0; i<3; i++){
//            chNotices = challengeService.selectAll();
//        //}
//    @RequestMapping("/test")
//    @ResponseBody
//    public String test(){
//       challengeService.selectAll();
//        return "";
//    }
//    @RequestMapping("/Challenge/challenge_certify")
//    public String ChallengeCertify(){
//
//        return "Challenge_certify";
//    }

//    @RequestMapping("/Challenge/certifyCommentAdd")
//    public String ChallengeCertifyCommentAdd(){
//        return "Challenge_certify_comment_add";
//    }

}
