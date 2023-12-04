package ru.mooncess.pizzeria_rbd.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mooncess.pizzeria_rbd.dto.PostDto;
import ru.mooncess.pizzeria_rbd.entity.Post;
import ru.mooncess.pizzeria_rbd.repository.PostRepository;

import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository {
    private final JdbcTemplate jdbcTemplate;

    public PostRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PostDto> getAllPost() {
        String sql = "SELECT post.id_post, post.name_of_post, post.salary, department.name_of_department " +
                "FROM post " +
                "JOIN department ON post.department_id_department = department.id_department;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PostDto.class));
    }

    @Override
    public PostDto getPostById(Integer id) {
        String sql = "SELECT post.id_post, post.name_of_post, post.salary, department.name_of_department " +
                "FROM post " +
                "JOIN department ON post.department_id_department = department.id_department WHERE id_post = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(PostDto.class));
    }

    @Override
    public void createPost(Post post) {
        String sql = "INSERT INTO post (name_of_post, salary, department_id_department) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, post.getNameOfPost(), post.getSalary(), post.getDepartment().getIdDepartment());
    }

    @Override
    public void updatePost(Post post) {
        String sql = "UPDATE post SET name_of_post = ?, salary = ?, department_id_department = ? WHERE id_post = ?";
        jdbcTemplate.update(sql, post.getNameOfPost(), post.getSalary(), post.getDepartment().getIdDepartment(), post.getIdPost());
    }

    @Override
    public void deletePostById(Integer id) {
        String sql = "DELETE FROM post WHERE id_post = ?";
        jdbcTemplate.update(sql, id);
    }
}
