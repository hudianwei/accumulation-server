package com.accumulation.security;

import com.accumulation.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

/**
 * @Description:
 * @author: HU
 * @date: 2018/9/20 19:20
 */
@SuppressWarnings("ALL")
@Component
public class MyUserDetailsService implements UserDetailsService {
    Logger log= LoggerFactory.getLogger(MyUserDetailsService.class);
    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
        collection.add(new SimpleGrantedAuthority("USERTEST"));
        User user = new User("hudianwei", "123", collection);
        return user;
    }
}
