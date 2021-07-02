package com.shandrikov.market.market_project;

import com.shandrikov.market.market_project.user.User;
import com.shandrikov.market.market_project.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setUsername("tom");
        user.setPassword("tom");
        user.setRole("USER");
        user.setEnabled(true);

        User savedUser = userRepo.save(user);
        User existUser = entityManager.find(User.class, savedUser.getId());
        assertThat(existUser.getPassword()).isEqualTo(user.getPassword());
    }
}
