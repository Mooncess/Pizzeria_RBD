package ru.mooncess.pizzeria_rbd.repository;

import org.springframework.security.core.userdetails.UserDetails;
import ru.mooncess.pizzeria_rbd.entity.User;

public interface UserRepository {
    UserDetails loadUserByUsername(String username);

    void createUser(User user);

    public Long loadIdByUsername(String username);
}
