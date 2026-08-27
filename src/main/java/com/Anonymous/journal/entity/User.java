package com.Anonymous.journal.entity;

import lombok.Data;

import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//this class we will connect user with journal entry || we'll add user login
@Document(collection = "users")
@Data
public class User {  //this is called POJO : plain old java obj
  @Id
  private ObjectId id;

  @Indexed(unique = true) //Mark field indexed by MongoDB's indexing feature (by it searching get fast) || unique is true
  //for creating auto indexing -> check application.propertis
  @NonNull // this is from lombok || if get null then we get null pointer exception
  private String userName;
  @NonNull
  private  String password;

  @DBRef //list of je keep reference of Entrys from JournalEntry || by it User connect to their journal entry
  private List<JournalEntry> je = new ArrayList<>();

  private List<String> roles;


}
