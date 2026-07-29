package com.Anonymous.journal.cantroler;

import com.Anonymous.journal.entity.User;
import com.Anonymous.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController { // This is file where all Req can Done without Login

    @Autowired
    private UserService UServ;

    @GetMapping("/health-check")
    public String helthCheck(){
        return "OK";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        UServ.saveNewUser(user);
    }
}
