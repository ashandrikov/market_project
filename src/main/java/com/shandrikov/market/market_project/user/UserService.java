package com.shandrikov.market.market_project.user;

import com.shandrikov.market.market_project.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public User getUserByUsername(String username){
        User user = null;
        user = userRepo.getUserByUsername(username);
        return user;
    }

}
