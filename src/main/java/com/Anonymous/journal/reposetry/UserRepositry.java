package com.Anonymous.journal.reposetry;

import com.Anonymous.journal.entity.JournalEntry;
import com.Anonymous.journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositry extends MongoRepository<User, ObjectId>{
    // this extends with MongoRepository <- it do all data management by its predefine methods
    User findByUserName(String userName);
    //  |^^^^^^^^^^^^^^| findBy is predefine methode
    void deleteByUserName(String userName);
}
