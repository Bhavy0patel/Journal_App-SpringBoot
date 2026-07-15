package com.Anonymous.journal.cantroler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelthCheck {
    @GetMapping("/health-check")
    public String helthCheck(){
        return "OK";
    }
}
