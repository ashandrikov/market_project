package com.shandrikov.market.market_project;

import com.shandrikov.market.market_project.category.Category;
import com.shandrikov.market.market_project.category.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository repo;

    @Test
    public void testCreateCategory(){
        Category savedCategory = repo.save(new Category(4, "Водный спорт"));
        assertThat(savedCategory.getId()).isGreaterThan(0);
    }
}
