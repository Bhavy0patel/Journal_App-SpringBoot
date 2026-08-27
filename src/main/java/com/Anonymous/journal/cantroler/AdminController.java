package com.Anonymous.journal.cantroler;

import com.Anonymous.journal.entity.User;
import com.Anonymous.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService UServ;

    //this will get all user
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> all = UServ.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //At least one ADMIN to Access these Controllers; 1st ADMIN Created manually in DB
    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody User user){
        UServ.saveAdmin(user);
    }
}
