package com.example.lab10.Controller;

import com.example.lab10.Model.Categories;
import com.example.lab10.Service.CategoriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoriesService service;

    @GetMapping("/get")
    public ResponseEntity getCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getCategories());
    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Categories category, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        service.addCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body("Category added");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){
        service.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted");
    }
}
