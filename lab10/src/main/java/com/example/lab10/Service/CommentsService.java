package com.example.lab10.Service;

import com.example.lab10.Api.ApiException;
import com.example.lab10.Model.Comments;
import com.example.lab10.Model.Post;
import com.example.lab10.Model.User;
import com.example.lab10.Repository.CommentsRepository;
import com.example.lab10.Repository.PostRepository;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository repository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;



    public List<Comments> getComments(){
        return repository.findAll();
    }


    public void addComment(Integer authorId,Integer postId,Comments comments){
        User user = userRepository.findUserById(authorId);
        Post post = postRepository.findPostById(postId);

        if (user == null){
            throw new ApiException("Invalid author id");
        }
        if (post == null){
            throw new ApiException("Invalid Post id");
        }
        comments.setAuthor_id(user.getId());
        comments.setPost_id(post.getId());
        repository.save(comments);
    }

    public void updateComment(Integer comment_id, Comments comments){
        Comments comments1 = repository.findCommentsById(comment_id);

        if (comments1 ==null){
            throw new ApiException("Invalid comment id");
        }
        comments1.setComment_date(LocalDateTime.now());
        comments1.setContent(comments.getContent());
        repository.save(comments1);
    }

    public void deleteComment(Integer id){
        Comments comment = repository.findCommentsById(id);
        if (comment ==null){
            throw new ApiException("Invalid Comment id");
        }
        repository.delete(comment);
    }

    public List<Comments> findCommentsByPostId(Integer id){
        List<Comments> comments = repository.commentsFindByPostID(id);
        if (comments.isEmpty()){
            throw new ApiException("Invalid post id");
        }
        return comments;
    }

    public List<Comments> findCommentsContaining(String word){
        List<Comments> comments = repository.findCommentsByContentContaining(word);
        if (comments.isEmpty()){
            throw new ApiException("Invalid word, or comments doesn't contain: "+word);
        }
        return comments;
    }

    public List<Comments> commentsByDay(int year,int month,int day ){
        List<Comments> comments = repository.commentsByDay(year,month,day);
        if (comments.isEmpty()){
            throw new ApiException("Invalid Date");
        }
        return comments;
    }




}
