package com.example.lab10.Repository;

import com.example.lab10.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserById(Integer id);


    @Query("select d from User d where year(d.registration_date) = :year ")
    List<User> usersFindByRegistrationYear(@Param("year") int year);

    @Query("select d from User d where d.registration_date < ?1")
    List<User> beforeDateUsers(LocalDate date);

}