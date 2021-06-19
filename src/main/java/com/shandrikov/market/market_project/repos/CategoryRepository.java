package com.shandrikov.market.market_project.repos;

import com.shandrikov.market.market_project.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
