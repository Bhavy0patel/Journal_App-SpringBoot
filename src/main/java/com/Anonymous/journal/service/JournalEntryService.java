package com.Anonymous.journal.service;

import com.Anonymous.journal.entity.JournalEntry;
import com.Anonymous.journal.entity.User;
import com.Anonymous.journal.reposetry.JournalEntryRepositry;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j// used for 'log' at saveEntry methode
public class JournalEntryService {

    @Autowired
    private JournalEntryRepositry jeRepo;
    // we inject JournalEntryRepositry in to this class by above 2 lines

    @Autowired
    private UserService UServ;

//for save entry Used when we POST data
    //this use
    @Transactional
    public  void saveEntry(JournalEntry je, String userName){
      try {
          je.setDate(LocalDateTime.now());
          JournalEntry saved = jeRepo.save(je);
          User user = UServ.findByUSerName(userName);
          user.getJe().add(saved);
          UServ.saveEntry(user);
      } catch (Exception e){
          System.out.println(e);
          throw new RuntimeException("An error Occurred while Saving Entry",e);
      //    log.error("Exception ",e); //'log' is from lombok using @Slf4j
      }
    }

    public  void saveEntry(JournalEntry je){
        jeRepo.save(je);
    }

//used when we GET data
    public List<JournalEntry> getAll(){
       return jeRepo.findAll();
    }

//used when we use FIND_BY_OBJ_ID
    public Optional<JournalEntry> findById(ObjectId Id){
        return jeRepo.findById(Id);
    }

//used when we use DELETE_BY_OBJID
    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed=false;
        try {
            User user = UServ.findByUSerName(userName);
            removed = user.getJe().removeIf(x -> x.getId().equals(id));
            // if we dont write this(^) statement then id delete from JE but not from User
            // in next save of user all deleted id will remove (spring update it at next save)
            //but by this statement we update user too at the moment of delete
            if (removed) {
                UServ.saveEntry(user); //we do save for same id so it will update
                jeRepo.deleteById(id);
            }
        } catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("An Erroe occur while deleting entry",e);
        }
        return removed;
    }

}
