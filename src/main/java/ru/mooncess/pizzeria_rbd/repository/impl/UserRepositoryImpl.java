package ru.mooncess.pizzeria_rbd.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import ru.mooncess.pizzeria_rbd.entity.User;
import ru.mooncess.pizzeria_rbd.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        User user = jdbcTemplate.queryForObject("SELECT username, password, roleUser FROM user_ WHERE username = '" + username + "'", new BeanPropertyRowMapper<>(User.class));
        return user;
    }

    @Override
    public Long loadIdByUsername(String username){
        long id = jdbcTemplate.queryForObject("SELECT id FROM user_ WHERE username = '" + username + "'", Long.class);
        return id;
    }

    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO user_ (username, password, roleUser) " +
                "VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), 1);
    }
}
