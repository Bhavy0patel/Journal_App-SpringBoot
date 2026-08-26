package com.Anonymous.journal.cantroler;

import com.Anonymous.journal.entity.JournalEntry;
import com.Anonymous.journal.entity.User;
import com.Anonymous.journal.service.JournalEntryService;
import com.Anonymous.journal.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService jeService;

    @Autowired
    private UserService UServ;

//for GET(output) ALL entry
    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        Authentication Auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = Auth.getName();
        User user = UServ.findByUSerName(userName);
        List<JournalEntry> all =user.getJe();  //this getJe is from lom book
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//for POST(enter entry)
    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
          try {
              Authentication Auth = SecurityContextHolder.getContext().getAuthentication();
              String userName = Auth.getName();
              jeService.saveEntry(myEntry,userName);
              return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
          } catch (Exception e){
              return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
          }
    }

//for GET(output) entry by ID
    @GetMapping("/id/{myid}")
    public ResponseEntity<JournalEntry> getJrnlEnrnyByID(@PathVariable ObjectId myid) {
        //here on catch : if you use Ram Uname,pw and give shyam's ID according tu current struct i will show it, which is wrong
        Authentication Auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = Auth.getName();
        User user = UServ.findByUSerName(userName);
        List<JournalEntry> collect =user.getJe().stream().filter(x->x.getId().equals(myid)).toList();
        if(!collect.isEmpty()){
            Optional<JournalEntry> RequestedJE = jeService.findById(myid);
            if(RequestedJE.isPresent()){
                return new ResponseEntity<>(RequestedJE.get(),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//for DELETE by ID2
    @DeleteMapping("/id/{myid}")
    public ResponseEntity<?> DeleteEnryBtID(@PathVariable ObjectId myid) {
        Authentication Auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = Auth.getName();
        boolean removed = jeService.deleteById(myid,userName);
        if(removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//for update by ID
    @PutMapping("/id/{myid}")
    public ResponseEntity<?> UpdateByID(@PathVariable ObjectId myid, @RequestBody JournalEntry NewEntry) {
        Authentication Auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = Auth.getName();
        User user = UServ.findByUSerName(userName);
        List<JournalEntry> collect =user.getJe().stream().filter(x->x.getId().equals(myid)).toList();
        if(!collect.isEmpty()){
          Optional<JournalEntry> RequestedJE = jeService.findById(myid);
            if(RequestedJE.isPresent()){
              JournalEntry old = RequestedJE.get();
              //here if you are sending title then change only given content other remain same (for both title and content)
              old.setTitle(NewEntry.getTitle() != null && !NewEntry.getTitle().equals(" ")?NewEntry.getTitle(): old.getTitle());
              old.setContant(NewEntry.getContant() != null && !NewEntry.getContant().equals(" ")?NewEntry.getContant(): old.getContant());
              jeService.saveEntry(old);
              return new ResponseEntity<>(old,HttpStatus.OK);
            }
        }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
