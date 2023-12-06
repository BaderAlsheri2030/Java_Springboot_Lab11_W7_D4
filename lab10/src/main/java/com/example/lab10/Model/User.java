package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "username cannot be null")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;
    @NotNull(message = "email cannot be null")
    @Email(message = "must be a valid email")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;
    @NotNull(message = "password cannot be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;
    @Column
    private LocalDate registration_date = LocalDate.now();

}
