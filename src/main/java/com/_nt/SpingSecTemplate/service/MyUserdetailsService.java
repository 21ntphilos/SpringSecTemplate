package com._nt.SpingSecTemplate.service;

import com._nt.SpingSecTemplate.model.User;
import com._nt.SpingSecTemplate.model.UserPrincipal;
import com._nt.SpingSecTemplate.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserdetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);

        if(user == null) {
            System.out.println("User not Found");
            throw new UsernameNotFoundException("User not Found");
        }
        //since we have to return a class implementing the userdetials we have to create our own userDetails classe to return it
        return new UserPrincipal(user);
    }
}
