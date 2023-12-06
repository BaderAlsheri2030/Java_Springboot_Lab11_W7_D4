package com.example.lab10.Service;

import com.example.lab10.Api.ApiException;
import com.example.lab10.Model.User;
import com.example.lab10.Repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;


    public List<User> getUsers(){
        return repository.findAll();
    }


    public void addUser(User user){
        repository.save(user);
    }

    public void updateUser(Integer id, User user){
        User user1 = repository.findUserById(id);
        if (user1 == null){
            throw new ApiException("Invalid user id");
        }
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setUsername(user.getUsername());
        repository.save(user1);
    }

    public void deleteUser(Integer id){
        User user = repository.findUserById(id);
        if (user == null){
            throw new ApiException("Invalid id");
        }
        repository.delete(user);
    }

    public List<User> findUsersByYear(int year){
        List<User> users = repository.usersFindByRegistrationYear(year);
        if (users.isEmpty()){
            throw new ApiException("Invalid Year");
        }
        return users;
    }

    public List<User> findUsersBeforeDate(LocalDate date){
        List<User> users = repository.beforeDateUsers(date);
        if(users.isEmpty()){
            throw new ApiException("Invalid date");
        }
        return users;
    }

}
