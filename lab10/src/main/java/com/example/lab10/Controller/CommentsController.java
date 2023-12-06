package com.example.lab10.Controller;

import com.example.lab10.Model.Comments;
import com.example.lab10.Service.CommentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comment")
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService service;


    @GetMapping("/get")
    public ResponseEntity getComments(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getComments());
    }

    @PostMapping("/add/{author_id}/{post_id}")
    public ResponseEntity addComment(@PathVariable Integer author_id, @PathVariable Integer post_id, @Valid @RequestBody Comments comment, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        service.addComment(author_id,post_id,comment);
        return ResponseEntity.status(HttpStatus.OK).body("Comment added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id, @Valid @RequestBody Comments comment, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        service.updateComment(id,comment);
        return ResponseEntity.status(HttpStatus.OK).body("Comment updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id){
        service.deleteComment(id);
        return ResponseEntity.status(HttpStatus.OK).body("Comment updated");
    }

    @GetMapping("/commentsbyid/{id}")
    public ResponseEntity getCommentsByPostId(@PathVariable Integer id){
       return ResponseEntity.status(HttpStatus.OK).body(service.findCommentsByPostId(id));
    }

    @GetMapping("/comments/{word}")
    public ResponseEntity getCommentsContaining(@PathVariable String word){
        return ResponseEntity.status(HttpStatus.OK).body(service.findCommentsContaining(word));
    }

    @GetMapping("/date/{year}/{month}/{day}")
    public ResponseEntity getCommentByDay(@PathVariable int year,@PathVariable int month, @PathVariable int day){
        return ResponseEntity.status(HttpStatus.OK).body(service.commentsByDay(year,month,day));
    }




}
