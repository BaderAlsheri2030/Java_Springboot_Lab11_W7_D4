package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "int not null")
    private Integer post_id;
    @Column(columnDefinition = "int not null")
    private Integer author_id;
    @NotNull(message = "content cannot be null")
    @Column(columnDefinition = "varchar(120) not null")
    private String content;
    @Column
    private LocalDateTime comment_date = LocalDateTime.now();
}
