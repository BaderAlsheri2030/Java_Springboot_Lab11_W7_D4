package com.example.lab10.Controller;

import com.example.lab10.Model.Post;
import com.example.lab10.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @GetMapping("/get")
    public ResponseEntity getPosts(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getPosts());
    }


    @PostMapping("/add/{author_id}/{category_id}")
    public ResponseEntity addPost(@PathVariable Integer author_id,@PathVariable Integer category_id, @Valid @RequestBody Post post, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        service.addPost(author_id,category_id,post);
        return ResponseEntity.status(HttpStatus.OK).body("post Added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id, @Valid @RequestBody Post post, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        service.updatePost(id,post);
        return ResponseEntity.status(HttpStatus.OK).body("Post updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id){
        service.deletePost(id);
        return ResponseEntity.status(HttpStatus.OK).body("post deleted");
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity findPostsById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getPostsByAuthorId(id));
    }

    @GetMapping("/containing/{word}")
    public ResponseEntity findPostsContainingWord(@PathVariable String word){
        return ResponseEntity.status(HttpStatus.OK).body(service.getPostsContaining(word));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity findPostsByCategoryName(@PathVariable String category){
        return ResponseEntity.status(HttpStatus.OK).body(service.getPostsByCategoryName(category));
    }



}
