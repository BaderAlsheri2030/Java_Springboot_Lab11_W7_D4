package com.example.lab10.Repository;

import com.example.lab10.Model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments,Integer> {

    Comments findCommentsById(Integer id);

    @Query("select c from Comments c where c.post_id =?1")
    List<Comments> commentsFindByPostID(Integer id);

    List<Comments> findCommentsByContentContaining(String word);

    @Query("select d from Comments d where year(d.comment_date) =:year and month(d.comment_date) = :month and day(d.comment_date) =:day")
    List<Comments> commentsByDay(@Param("year") int year,@Param("month") int month,@Param("day") int day);

}
