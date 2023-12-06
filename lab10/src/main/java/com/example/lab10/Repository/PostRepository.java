package com.example.lab10.Repository;

import com.example.lab10.Model.Comments;
import com.example.lab10.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    Post findPostById(Integer id);

    @Query("select p from Post p where p.author_id = ?1")
    List<Post> postByAuthorID(Integer id);

    List<Post> findPostByContentContaining(String word);

    @Query("select name from Post name where name.category_name =?1")
    List<Post> findPostByCategory_name(String Category);

}
