package com.Anonymous.journal.reposetry;

import com.Anonymous.journal.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.jar.JarEntry;

public interface JournalEntryRepositry extends MongoRepository<JournalEntry, ObjectId>{
    // this extands with MongoRepository <- it do all data managemant by its pedefine mathodes
}
