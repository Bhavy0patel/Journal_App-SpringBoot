package com.Anonymous.journal.service;

import com.Anonymous.journal.entity.User;
import com.Anonymous.journal.reposetry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepositry UReop;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = UReop.findByUserName(username);
        if(user!=null){
            UserDetails UDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0])) //converting Roles to StringArray
                    .build();
            return UDetails;
        }
        throw new UsernameNotFoundException("user NOT Found with User Name "+username);
    }
}
