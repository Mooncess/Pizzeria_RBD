package ru.mooncess.pizzeria_rbd.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import ru.mooncess.pizzeria_rbd.dto.ClientCreateDto;
import ru.mooncess.pizzeria_rbd.dto.ClientDto;
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
    public List<ClientDto> getAllClients() {
        String sql = "SELECT * FROM client " +
                "JOIN full_name ON client.full_name_id_full_name = full_name.id_full_name " +
                "JOIN phone_number ON client.phone_number_id_phone_number = phone_number.id_phone_number;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ClientDto.class));
    }

    @Override
    public ClientDto getClientById(Integer id) {
        String sql = "SELECT * FROM client " +
                "JOIN full_name ON client.full_name_id_full_name = full_name.id_full_name " +
                "JOIN phone_number ON client.phone_number_id_phone_number = phone_number.id_phone_number WHERE id_client = ?;";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(ClientDto.class));
    }

    @Override
    public ClientDto getClientByUserId(Long id) {
        String sql = "SELECT * FROM client " +
                "JOIN full_name ON client.full_name_id_full_name = full_name.id_full_name " +
                "JOIN phone_number ON client.phone_number_id_phone_number = phone_number.id_phone_number WHERE user_id_user = ?;";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(ClientDto.class));
    }

    @Override
    public void createClient(ClientCreateDto client) {
        String sql = "CALL CreateClient(?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, client.firstName, client.lastName, client.surname, client.phoneNumber, client.userId);
    }

    @Override
    public void updateClient(ClientDto client) {
        String sql = "CALL UpdateClient(?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, client.idClient, client.firstName, client.lastName, client.surname, client.phoneNumber);
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
