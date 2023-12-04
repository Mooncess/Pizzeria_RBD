package ru.mooncess.pizzeria_rbd.repository;

import ru.mooncess.pizzeria_rbd.dto.PostDto;
import ru.mooncess.pizzeria_rbd.entity.Post;

import java.util.List;

public interface PostRepository {
    List<PostDto> getAllPost();
    PostDto getPostById(Integer id);
    void createPost(Post Post);
    void updatePost(Post Post);
    void deletePostById(Integer id);
}
