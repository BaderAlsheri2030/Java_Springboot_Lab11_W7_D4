package com.example.lab10.Service;

import com.example.lab10.Api.ApiException;
import com.example.lab10.Model.Categories;
import com.example.lab10.Repository.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoriesRepository repository;


    public List<Categories> getCategories(){
        return repository.findAll();
    }


    public void addCategory(Categories category){
         repository.save(category);
    }

    public void deleteCategory(Integer id){
        Categories category = repository.findCategoriesById(id);
        if (category == null){
            throw new ApiException("Invalid category id");
        }
        repository.delete(category);
    }
}
