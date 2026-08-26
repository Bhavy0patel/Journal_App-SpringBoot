package com.Anonymous.journal.cantroler;

import com.Anonymous.journal.entity.User;
import com.Anonymous.journal.reposetry.UserRepositry;
import com.Anonymous.journal.service.UserService;
import org.springframework.security.core.Authentication;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService UServ;

    @Autowired
    private UserRepositry UserRepo;


    @PutMapping
    public  ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication Auth = SecurityContextHolder.getContext().getAuthentication();
        //when user get Authenticated he details stored in SecurityContextHolder
        String userName = Auth.getName();
        // By ^ this we get UserName PW from postman when we Auth our self
        //so no need to take by RequestMapping
        User userInDB = UServ.findByUSerName(userName);
        userInDB.setUserName(user.getUserName());
        userInDB.setPassword(user.getPassword());
        UServ.saveNewUser(userInDB);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUseByID(){
        Authentication Auth = SecurityContextHolder.getContext().getAuthentication();
        UserRepo.deleteByUserName(Auth.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
