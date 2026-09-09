package com.Anonymous.journal.service;

import com.Anonymous.journal.reposetry.UserRepositry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import com.Anonymous.journal.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

//@SpringBootTest  <-- Dont use it when mock is used
public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl UDServ;

    @Mock
    private UserRepositry UReop;

    @BeforeEach
    void IntializeMocks(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername(){
        //Dynamic way can get any user name
        when(UReop.findByUserName(ArgumentMatchers.anyString()))
                .thenReturn(User.builder().userName("shyam").password("kjbnjknh").roles(new ArrayList<>()).build());

        //static way only for "ram"
//        when(UReop.findByUserName("shyam"))
//                .thenReturn((com.Anonymous.journal.entity.User) User.builder().userName("shyam").password("kjbnjknh").build());


        UserDetails user = UDServ.loadUserByUsername("shyam");
        Assertions.assertNotNull(user);
    }
}
