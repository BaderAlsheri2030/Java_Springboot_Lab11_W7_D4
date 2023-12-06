package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer author_id;
    @Column
    private Integer category_id;
    @NotNull(message = "title cannot be null")
    @Column
    private String category_name;
    @Column(columnDefinition = "varchar(20) not null")
    private String title;
    @NotNull(message = "content cannot be null")
    @Column(columnDefinition = "varchar(200) not null")
    private String content;
    @Column
    private LocalDate publication_date = LocalDate.now();
}
