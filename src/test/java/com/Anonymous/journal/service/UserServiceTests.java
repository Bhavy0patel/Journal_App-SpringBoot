package com.Anonymous.journal.service;

import com.Anonymous.journal.reposetry.UserRepositry;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.internal.matchers.Equals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepositry URepo;

    @BeforeAll  // opposite kind of Annotation is <-> '@AfterAll'
    public static void B4All(){
        // Runs one time before all test cases
        // it should be static methode

    }

    @BeforeEach  // opposite kind of Annotation is <-> '@AfterEach'
    public void B4Each(){
        //run this Test case before each time test case run
        // EX : updating CSV Before Each test case runs
    }

    @Disabled
    @Test
    public void testfindByUSerName(){
        assertEquals(4,2+2); // <-- this just for Ex
    }

    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2,10,12",
            "3,3,6"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected,a+b);
    }

}
