package com.shandrikov.market.market_project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public User getCurrentlyLoggedInUser(Authentication authentication){
        if (authentication == null) return null;

        User user = null;
        Object principal = authentication.getPrincipal();

        if (principal instanceof MyUserDetails){
            user = ((MyUserDetails)principal).getUser();
        }

        return user;
    }

}
