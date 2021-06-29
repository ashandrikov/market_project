package com.shandrikov.market.market_project;

import com.shandrikov.market.market_project.entity.Item;
import com.shandrikov.market.market_project.entity.Role;
import com.shandrikov.market.market_project.entity.User;
import com.shandrikov.market.market_project.repos.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Collections;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
//    @Rollback(false)
    void insertItem() throws IOException {
        File file = new File("D:\\ARTEM\\5 Развлечения\\Картинки\\forest.jpg");

        Item item = new Item();
        item.setName(file.getName());

        User testUser = new User();
        testUser.setId(1);
        testUser.setUsername("test");
        testUser.setPassword("test");
//        testUser.setActive(true);
//        testUser.setRoles(Collections.singleton(Role.USER));
//        testUser.getRoles().clear();
//        testUser.getRoles().add(Role.ADMIN);
//                .add(Role.valueOf("USER"));

//        byte[] bytes = Files.readAllBytes(file.toPath());
//        item.setImage(bytes);

        byte[] data = Files.readAllBytes(file.toPath());
        String imageString = Base64.getEncoder().encodeToString(data);
        item.setImage(imageString);

        item.setName("fgh");
        item.setPrice(1000);
        item.setDescription("description");

        Item savedItem = itemRepository.save(item);
        Item existItem = entityManager.find(Item.class, savedItem.getId());
    }

}
