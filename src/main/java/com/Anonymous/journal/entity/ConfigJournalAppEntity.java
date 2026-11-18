package com.Anonymous.journal.entity;

import lombok.Builder;
import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;


//it is mainly for that Api to DB vi cache
@Document(collection = "Config_journal_app")
@Data
@Builder
public class ConfigJournalAppEntity {  //this is called POJO : plain old java obj

  private String key;
  private String value;

}
