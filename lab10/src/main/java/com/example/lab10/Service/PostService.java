package com.example.lab10.Service;

import com.example.lab10.Api.ApiException;
import com.example.lab10.Model.Categories;
import com.example.lab10.Model.Post;
import com.example.lab10.Model.User;
import com.example.lab10.Repository.CategoriesRepository;
import com.example.lab10.Repository.PostRepository;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;
    private final UserRepository userRepository;
    private final CategoriesRepository categoriesRepository;

    public List<Post> getPosts(){
       return repository.findAll();
    }

    public void addPost(Integer user_id,Integer category_id,Post post){
        User user = userRepository.findUserById(user_id);
        Categories category = categoriesRepository.findCategoriesById(category_id);
        if (user == null){
            throw new ApiException("Invalid author id");
        }
        if (category == null){
            throw new ApiException("Invalid category id");
        }
        post.setCategory_id(category.getId());
        post.setCategory_name(category.getCategory_name());
        post.setAuthor_id(user.getId());
        repository.save(post);
    }

    public void updatePost(Integer id,Post post){
        Post post0 = repository.findPostById(id);
        if (post0 ==null){
            throw new ApiException("Invalid Post id");
        }
        post0.setContent(post.getContent());
        post0.setTitle(post.getTitle());
        repository.save(post0);
    }

    public void deletePost(Integer id){
        Post post = repository.findPostById(id);
        if (post == null){
            throw new ApiException("Invalid Post id");
        }
        repository.delete(post);
    }

    public List<Post> getPostsByAuthorId(Integer id){
        List<Post> posts = repository.postByAuthorID(id);
        if (posts.isEmpty()){
            throw new ApiException("Invalid author id");
        }
        return posts;
    }

    public List<Post> getPostsContaining(String word){
        List<Post> posts = repository.findPostByContentContaining(word);
        if (posts.isEmpty()){
            throw new ApiException("the word you entered doesn't exists in posts contents");
        }
        return posts;
    }

    public List<Post> getPostsByCategoryName(String category){
        List<Post> posts = repository.findPostByCategory_name(category);
        if (posts.isEmpty()){
            throw new ApiException("the category you entered doesn't exists in posts categories");
        }
        return posts;
    }


}
