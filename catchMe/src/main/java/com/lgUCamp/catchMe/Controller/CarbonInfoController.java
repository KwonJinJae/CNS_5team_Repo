package com.lgUCamp.catchMe.Controller;

import com.lgUCamp.catchMe.Service.CarbonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CarbonInfoController {

    @Autowired
    CarbonInfoService carbonInfoService;

    @RequestMapping("/carbonInfo/carbonInfoList")
    public String carbonInfoList (Model model) {

        model.addAttribute("carbonList", carbonInfoService.carbonInfoList());

        return "carbonInfo/carbonInfoList";
    }
}
