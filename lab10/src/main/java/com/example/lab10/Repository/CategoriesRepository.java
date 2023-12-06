package com.example.lab10.Repository;

import com.example.lab10.Model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Integer> {

    Categories findCategoriesById(Integer id);
}
