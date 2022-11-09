package com.lgUCamp.catchMe.Comtroller;

import com.lgUCamp.catchMe.DTO.AdminCarbonFile;
import com.lgUCamp.catchMe.DTO.CarbonInfo;
import com.lgUCamp.catchMe.DTO.CarbonInfoFile;
import com.lgUCamp.catchMe.Service.CarbonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Controller
public class CarbonInfoController {

    @Autowired
    CarbonInfoService carbonInfoService;

    /* 탄소중립 전체 게시글 조회 - 최신 날짜순 정렬 */
    @RequestMapping("/carbonInfo/carbonInfoList")
    public String carbonInfoList (Model model) {

        model.addAttribute("carbonInfoList", carbonInfoService.carbonInfoList());

        return "carbonInfo/carbonInfoList";
    }

    /* 탄소중립 상세 게시글 조회 */
    @RequestMapping("/carbonInfo/carbonInfoDetail")
    public String carbonInfoDetail (@RequestParam int infoNo, Model model,
                                    HttpServletRequest request, HttpServletResponse response){

        Cookie[] cookies =request.getCookies();

        // 번호는 해석 순서입니다. 로직 진행 순서는 이대로 해주세요
        // 쿠키를 담을 수 있는 변수를 만들고, request로 받아온 쿠키 중에 infoView가 있는지 확인
        Cookie oldCookie = null;
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("infoView")){
                    oldCookie = cookie;
                }
            }
        }

        // 2. 이미 조회한 사람일 경우
        if(oldCookie != null){
            if(!oldCookie.getValue().contains("_[" + infoNo + "]")){
                this.carbonInfoService.updateCarbonInfoView(infoNo);
                oldCookie.setValue(oldCookie.getValue() + "_[" + infoNo + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);  // 쿠키 시간 설정
                response.addCookie(oldCookie);
            }

            // 1. 쿠키가 일치하지 않는 경우 = 새로 조회하는 사람일 경우
        } else {
            // 조회수 증가
            this.carbonInfoService.updateCarbonInfoView(infoNo);

            // infoView라는 이름으로 쿠키를 만들어준다. (다른 게시판은 꼭!!! 다른 이름 사용해주세요)
            Cookie newCookie = new Cookie("infoView", "_[" + infoNo + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(newCookie);
        }

        model.addAttribute("carbonInfo", carbonInfoService.carbonInfoDetail(infoNo));

        return "carbonInfo/carbonInfoDetail";
    }

    /* 탄소중립 글 등록 페이지 이동 */
    @GetMapping("/carbonInfo/carbonInfoRegist")
    public String carbonInfoRegist (Model model, HttpServletRequest request){

        /* 화면에 작성자명을 보여주기 위해 세션에서 로그인 정보를 받아
           관리자 테이블의 직무와 관리자번호 정보를 불러온다. */
        // 현재 날짜 구하기
        LocalDate now = LocalDate.now();

        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // 포맷 적용
        String today = now.format(formatter);

        model.addAttribute("today", today);

        return "carbonInfo/carbonInfoRegist";
    }

    /* 탄소 중립 게시글 등록 */
    @RequestMapping("carbonInfo/carbonInfoRegist/run")
    public String carbonInfoRegistRun (CarbonInfo carbonInfo){

        /* th:value 에 담아뒀던 관리자 번호를 DTO에 set 해주어 함께 insert 한다.
            지금은 임의로 1을 담아 insert한다.
         */
        int adminNo = 1;
        carbonInfo.setAdminNo(adminNo);

        carbonInfoService.carbonInfoInsert(carbonInfo);

        return "redirect:/carbonInfo/carbonInfoList";
    }

    /* 탄소 중립 게시글 수정을 위한 조회 */
    @RequestMapping("/carbonInfo/carbonInfoModifySelect/{infoNo}")
    public String carbonInfoModifySelect (@PathVariable int infoNo, AdminCarbonFile carbonInfo , Model model){

        model.addAttribute("carbonInfo", carbonInfo);

        return "carbonInfo/carbonInfoModify";
    }

    /* 탄소 중립 게시글 수정 */
    @PostMapping("/carbonInfo/carbonInfoModifySelect/run")
    public String carbonInfoModyfy (CarbonInfo carbonInfo, CarbonInfoFile carbonInfoFile){

        /* 업무 로직상 안전성을 위해 테이블 별로 나누어 수정한다. 트랜잭션 처리한다. */
        carbonInfoService.carbonInfoModify(carbonInfo);

        if(carbonInfoFile != null){

        }

        return "redirect:/carbonInfo/carbonInfoDetail?infoNo=" + carbonInfo.getInfoNo();
    }



    /* Ajax를 이용해 summernote 이미지 저장 */
    @RequestMapping(value="resources/static/carbonInfoImage")
    public ResponseEntity<?> summerimage(@RequestParam("file") MultipartFile img,
                                                      HttpServletRequest request) throws IOException {

        String path = request.getServletContext().getRealPath("resources/static/carbonInfoImage");
        Random random = new Random();

        long currentTime = System.currentTimeMillis();
        int	randomValue = random.nextInt(100);
        String fileName = Long.toString(currentTime) + "_"+randomValue+"_a_"+img.getOriginalFilename();

        File file = new File(path , fileName);
        img.transferTo(file);

        return ResponseEntity.ok().body("resources/static/carbonInfoImage"+fileName);

    }

//    @RequestMapping("resources/static/carbonInfoImage/delete")
//    public ResponseEntity<?> summerimageDelete(@RequestParam("file") MultipartFile img,
//                                                            HttpServletRequest request){
//
//
//
//    }



    @RequestMapping("/carbonInfo/carbonInfoDelete/{infoNo}")
    public String carbonInfoDelete (@PathVariable int infoNo, HttpServletRequest request){

        /* request의 session 정보와 작성자 정보를 비교해 본인만 삭제할 수 있도록 한다. */

        int deleteResult = carbonInfoService.carbonInfoDelete(infoNo);

        /* 파일명을 받아 null이 아닐 경우 파일도 삭제한다. */

        /* 모두 삭제되면 이미지 파일에서 이름을 비교해 일치하지 않는 파일을 삭제한다. */

        if (deleteResult == 1) {

            return "redirect:/carbonInfo/carbonInfoList";
        } else {

            // 에러페이지로 보내기
            return "redirect:/carbonInfo/carbonInfoList";
        }
    }





}
