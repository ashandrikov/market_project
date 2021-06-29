package com.shandrikov.market.market_project.repos;

import com.shandrikov.market.market_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("Select u from User u WHERE u.username = :username")
    User getUserByUsername(@Param("username") String username);

}


