package com.ivana.taskManager.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @GetMapping("/")
    public String homePage(){
        return "Hello task manager";
    }

}
