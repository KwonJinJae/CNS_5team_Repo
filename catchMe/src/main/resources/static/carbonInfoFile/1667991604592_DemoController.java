package com.example.demo.controller;

import com.example.demo.Book;
import com.example.demo.BookMapper;
import com.example.demo.service.DemoService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DemoController {

    @Autowired
    DemoService service;

    @Autowired
    BookMapper mapper;

    @RequestMapping("/books")
    @ResponseBody
    public List<Book> getBooks(){
        return mapper.selectAll();
    }


    @RequestMapping("/my") //application root --> http://localhost:8080/test, http://localhost:8080/dream/test
    //?paramname=name&paramname=paramvalue...
    @ResponseBody // http request, response
    public My test(String name, String address){
        System.out.println("Service Instance: "+service);
        System.out.println("name: "+name+", address: "+address);
        return My
                .builder()
                .name(name)
                .address(address)
                .build();
    }
    @PostMapping("/json")
    @ResponseBody
    public My jsonTest(@RequestBody My my)throws  Exception{
        my.setName(my.getName()+"님");
        Thread.sleep(10000);
        return my;
    }



    @RequestMapping("/test") //application root --> http://localhost:8080/test, http://localhost:8080/dream/test
    public String test1(){
        return "main1";
    }

    @PostMapping(value = "/post",consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    @ResponseBody
    public My post(@RequestBody My created){
        created.setName(created.getName()+"님.");
        return created;
    }
}
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class My{
    String name;
    String address;
}
