package com.shandrikov.market.market_project.repos;

import com.shandrikov.market.market_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}


