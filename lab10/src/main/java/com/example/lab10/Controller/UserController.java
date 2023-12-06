package com.example.lab10.Controller;

import com.example.lab10.Model.User;
import com.example.lab10.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.Date;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;


    @GetMapping("/get")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        service.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("User added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        service.updateUser(id,user);
            return ResponseEntity.status(HttpStatus.OK).body("user updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("user deleted" );
    }

    @GetMapping("/year/{year}")
    public ResponseEntity findUsersByYear(@PathVariable int year){
        return ResponseEntity.status(HttpStatus.OK).body(service.findUsersByYear(year));
    }

    @GetMapping("/before/{date}")
    public ResponseEntity findUsersBeforeDate(@PathVariable LocalDate date){
        return ResponseEntity.status(HttpStatus.OK).body(service.findUsersBeforeDate(date));
    }


}
