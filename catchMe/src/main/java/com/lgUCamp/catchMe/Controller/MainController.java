package com.lgUCamp.catchMe.Controller;

import com.lgUCamp.catchMe.Service.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @Autowired
    VideoServiceImpl videoService;
    @RequestMapping("/")
    public String main (Model model){
        model.addAttribute("videolist",videoService.videolist());

        return "main";
    }
}
