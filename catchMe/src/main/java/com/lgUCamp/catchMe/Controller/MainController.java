package com.lgUCamp.catchMe.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String main (){
        System.out.println("Main");
        return "main";
    }

}
