package com.Anonymous.journal.service;

import com.Anonymous.journal.entity.JournalEntry;
import com.Anonymous.journal.entity.User;
import com.Anonymous.journal.reposetry.JournalEntryRepositry;
import com.Anonymous.journal.reposetry.UserRepositry;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepositry UserRepo;
    // we inject JournalEntryRepositry in to this class by above 2 lines

//for save entry Used when we POST data
    public  void saveEntry(User u){
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
