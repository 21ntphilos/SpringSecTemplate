package com._nt.SpingSecTemplate;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

   @GetMapping
    public String greet(){
        return "Welcome to MY SEC APP";
    }




}
