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

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService jeService;

    @Autowired
    private UserService UServ;

//for GET(output) ALL entry
    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
        User user = UServ.findByUSerName(userName);
        List<JournalEntry> all =user.getJe();  //this getJe is from lom book
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//for POST(enter entry)
    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {
          try {
            jeService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
          } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

//for GET(output) entry by ID
    @GetMapping("/id/{myid}")
    public ResponseEntity<JournalEntry> getJrnlEnrnyByID(@PathVariable ObjectId myid) {
        Optional<JournalEntry> RequestedJE = jeService.findById(myid);
        if(RequestedJE.isPresent()){
            return new ResponseEntity<>(RequestedJE.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//for DELETE by ID2
    @DeleteMapping("/id/{userName}/{myid}")
    public ResponseEntity<?> DeleteEnryBtID(@PathVariable ObjectId myid,@PathVariable String userName) {
    //               here ^ not compulsory to give entity if there is nob entity then put '?'
        jeService.deleteById(myid,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//for update by ID
    @PutMapping("/id/{userName}/{myid}")
    public ResponseEntity<?> UpdateByID(
            @PathVariable ObjectId myid,
            @RequestBody JournalEntry NewEntry,
            @PathVariable String userName)
    {
        JournalEntry old = jeService.findById(myid).orElse(null);
        //here if you are sending title then change only given content other remain same (for both title and content)
        if(old!=null){
           old.setTitle(NewEntry.getTitle() != null && !NewEntry.getTitle().equals(" ")?NewEntry.getTitle(): old.getTitle());
           old.setContant(NewEntry.getContant() != null && !NewEntry.getContant().equals(" ")?NewEntry.getContant(): old.getContant());
         return new ResponseEntity<>(old,HttpStatus.OK);
        }
        jeService.saveEntry(old);
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
