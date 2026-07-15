package com.Anonymous.journal.entity;

import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {  //this is called POJO : plain old java obj
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String contant;
    private LocalDateTime date;

}
