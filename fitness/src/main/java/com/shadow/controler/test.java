package com.shadow.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class test {

    @RequestMapping(value = "in")
    public  String   test(){

        return "test";
    }

}
