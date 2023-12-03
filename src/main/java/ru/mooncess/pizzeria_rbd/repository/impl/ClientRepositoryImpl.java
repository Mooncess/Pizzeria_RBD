package ru.mooncess.pizzeria_rbd.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import ru.mooncess.pizzeria_rbd.dto.ClientCreateDto;
import ru.mooncess.pizzeria_rbd.entity.Client;
import ru.mooncess.pizzeria_rbd.entity.User;
import ru.mooncess.pizzeria_rbd.repository.ClientRepository;

import java.util.List;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    private final JdbcTemplate jdbcTemplate;

    public ClientRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Client> getAllClients() {
        String sql = "SELECT * FROM client";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Client.class));
    }

    @Override
    public Client getClientById(Integer id) {
        String sql = "SELECT * FROM client WHERE id_client = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Client.class));
    }

    @Override
    public void createClient(ClientCreateDto client) {
        String sql1 = "INSERT INTO full_name (`first_name`, `last_name`, `surname`) " +
                "VALUES (?, ?, ?)";
        jdbcTemplate.update(sql1, client.firstName, client.lastName, client.surname);
        System.out.println(client.surname);
        int fnId = jdbcTemplate.queryForObject("SELECT id_full_name FROM full_name WHERE first_name = '" +
                client.firstName + "' AND last_name = '" +
                client.lastName + "' AND surname = '" +
                client.surname + "'", Integer.class);

        String sql2 = "INSERT INTO phone_number (`phone_number`)" +
                "VALUES (?)";
        jdbcTemplate.update(sql2, client.phoneNumber);
        int phId = jdbcTemplate.queryForObject("SELECT id_phone_number FROM phone_number WHERE phone_number = " + client.phoneNumber, Integer.class);


        String sql = "INSERT INTO client (full_name_id_full_name, number_of_orders, personal_discount, phone_number_id_phone_number, user_id_user) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, fnId, 0, 0, phId, client.userId);
    }

    @Override
    public void updateClient(Client client) {
        String sql = "UPDATE client SET full_name_id_full_name = ?, number_of_orders = ?, personal_discount = ?, " +
                "phone_number_id_phone_number = ? WHERE id_client = ?";
        jdbcTemplate.update(sql, client.getFullName().getIdFullName(), client.getNumberOfOrders(),
                client.getPersonalDiscount(), client.getPhoneNumber().getIdPhoneNumber(), client.getIdClient());
    }

    @Override
    public void deleteClientById(Integer id) {
        String sql = "DELETE FROM client WHERE id_client = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteAllClients() {
        String sql = "DELETE FROM client";
        jdbcTemplate.update(sql);
    }

    @Override
    public List<Client> orderBy(String field, String asc) {
        String sql = "SELECT * FROM client ORDER BY " + field + " " + asc;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Client.class));
    }
}
