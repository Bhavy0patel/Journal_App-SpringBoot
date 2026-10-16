package com.Anonymous.journal.service;

import com.Anonymous.journal.entity.JournalEntry;
import com.Anonymous.journal.entity.User;
import com.Anonymous.journal.reposetry.JournalEntryRepositry;
import com.Anonymous.journal.reposetry.UserRepositry;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepositry UserRepo;
    // we inject JournalEntryRepositry in to this class by above 2 lines


    //used when new use created
    public static final PasswordEncoder PwEnc = new BCryptPasswordEncoder();

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    //we want only one instance become so we use final & static

//for save entry Used when we POST data
    public  void saveEntry(User u){
        UserRepo.save(u);
    }

    public  boolean saveNewUser(User u){
        try {
            u.setPassword(PwEnc.encode(u.getPassword()));
            u.setRoles(Arrays.asList("USER"));// currently only one role user
            UserRepo.save(u);
            return true;
        } catch(Exception e){
            logger.info("error | duplicate user entry");
            logger.error("error | error for {} :", u.getUserName(), e);
            log.error("error | error for {} :", u.getUserName(), e); // fom Slf4j -> lombok
            logger.warn("error | duplicate user entry");
            logger.debug("error | duplicate user entry"); // for use(print in log) it we need to co customization in logback.xml
            logger.trace("error | duplicate user entry"); // for use(print in log) it we need to co customization in logback.xml
            return false;
        }

    }

    public  void saveAdmin(User u){
        u.setPassword(PwEnc.encode(u.getPassword()));
        u.setRoles(Arrays.asList("USER","ADMIN"));// currently only one role user
        UserRepo.save(u);
    }

//used when we GET data
    public List<User> getAll(){
       return UserRepo.findAll();
    }

//used when we use FIND_BY_OBJ_ID
    public Optional<User> findById(ObjectId Id){
        return UserRepo.findById(Id);
    }

//used when we use DELETE_BY_OBJID
    public void deleteById(ObjectId Id){
        UserRepo.deleteById(Id);
    }

    public User findByUSerName(String userName){
        return UserRepo.findByUserName(userName);
    }

}
