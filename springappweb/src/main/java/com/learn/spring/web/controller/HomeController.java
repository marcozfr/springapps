package com.learn.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value={"/","/homepage"})
public class HomeController {

    @RequestMapping(method=RequestMethod.GET)
    public String home(){
        System.out.println("returning home...");
        return "home";
    }
    
}
