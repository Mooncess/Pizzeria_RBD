package ru.mooncess.pizzeria_rbd.service;

import org.springframework.stereotype.Service;
import ru.mooncess.pizzeria_rbd.dto.PostDto;
import ru.mooncess.pizzeria_rbd.entity.Post;
import ru.mooncess.pizzeria_rbd.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository PostRepository, UserService userService) {
        this.postRepository = PostRepository;
    }
    public List<PostDto> getAllPosts(){
        return postRepository.getAllPost();
    }
    public PostDto getPostById(int id){
        return postRepository.getPostById(id);
    }
    public void createPost(Post post){
        postRepository.createPost(post);
    }
    public void updatePost(Post Post){
        postRepository.updatePost(Post);
    }
    public void deletePostById(Integer id){
        postRepository.deletePostById(id);
    }
}
