package com.Anonymous.journal;

import com.mongodb.client.MongoDatabase;
import jdk.jfr.Enabled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication // <- this = 3 Annotation 1.ComponentScan, 2.EnableAutoConfiguration, 3.Configration
//by @Configration we can creat Bean in this class : REMEMBER
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args) {

		SpringApplication.run(JournalApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager KuchBhi(MongoDatabaseFactory MdbFactory){
		return new MongoTransactionManager(MdbFactory);
	}

}

//Implemented transactions(@Transactional) using local MongoDB replica set
//Tried Mogo Atlus but... Not preferd it to use