package com.shandrikov.market.market_project.category;

import com.shandrikov.market.market_project.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
