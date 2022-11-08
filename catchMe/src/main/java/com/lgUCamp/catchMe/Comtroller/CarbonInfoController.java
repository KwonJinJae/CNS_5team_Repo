package com.lgUCamp.catchMe.Comtroller;

import com.lgUCamp.catchMe.Service.CarbonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String carbonInfoDetail (@RequestParam int infoNo, Model model){



        model.addAttribute("carbonInfo", carbonInfoService.carbonInfoDetail(infoNo));

        System.out.println(carbonInfoService.carbonInfoDetail(infoNo));

        return "carbonInfo/carbonInfoDetail";
    }
}
