package com.Anonymous.journal.cantroler;

import com.Anonymous.journal.entity.JournalEntry;
import com.Anonymous.journal.entity.User;
import com.Anonymous.journal.service.JournalEntryService;
import com.Anonymous.journal.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService UServ;

    @GetMapping
    public List<User> getAllUser(){
       return UServ.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
       UServ.saveEntry(user);
    } 

    @PutMapping("/{userName}")
    public  ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String userName){
        User userInDB = UServ.findByUSerName(userName);
        if(userInDB!=null){
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            UServ.saveEntry(userInDB);
        }
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
