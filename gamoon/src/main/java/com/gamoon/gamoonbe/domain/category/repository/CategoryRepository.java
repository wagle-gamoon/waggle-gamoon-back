package com.gamoon.gamoonbe.domain.category.repository;

import com.gamoon.gamoonbe.domain.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategorySort(String categorySort);
}
