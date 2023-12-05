package ru.mooncess.pizzeria_rbd.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mooncess.pizzeria_rbd.dto.OrdersCreateDto;
import ru.mooncess.pizzeria_rbd.dto.OrdersDto;
import ru.mooncess.pizzeria_rbd.entity.Ingredient;
import ru.mooncess.pizzeria_rbd.entity.StatusOfOrder;
import ru.mooncess.pizzeria_rbd.repository.OrderRepository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<OrdersDto> getAllOrder() {
        String sql = "SELECT id_order, client_id_client, description, total_cost, status, date_of_creation FROM orders " +
                "JOIN status_of_order ON orders.status_of_order_id_status_of_order = status_of_order.id_status_of_order;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrdersDto.class));
    }

    @Override
    public List<OrdersDto> getAllOrderByClientId(int idClient) {
        String sql = "SELECT id_order, client_id_client, description, total_cost, status, date_of_creation FROM orders " +
                "JOIN status_of_order ON orders.status_of_order_id_status_of_order = status_of_order.id_status_of_order WHERE client_id_client = ?;";
        return jdbcTemplate.query(sql, new Object[]{idClient}, new BeanPropertyRowMapper<>(OrdersDto.class));
    }

    @Override
    public OrdersDto getOrderById(Integer id) {
        String sql = "SELECT id_order, client_id_client, description, total_cost, status, date_of_creation FROM orders " +
                "JOIN status_of_order ON orders.status_of_order_id_status_of_order = status_of_order.id_status_of_order WHERE id_order = ?;";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(OrdersDto.class));
    }

    @Override
    public void createOrder(OrdersCreateDto order) {
        String sql = "INSERT INTO orders (client_id_client, description, total_cost, status_of_order_id_status_of_order, date_of_creation) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, order.clientIdClient, order.description, order.totalCost, 1, order.dateOfCreation);
    }

    @Override
    public void updateOrder(OrdersCreateDto order) {
        String sql = "UPDATE orders SET status_of_order_id_status_of_order = ? WHERE id_order = ?";
        jdbcTemplate.update(sql, order.status, order.idOrder);
    }

    @Override
    public void deleteOrderById(Integer id) {
        String sql = "DELETE FROM order WHERE id_order = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<StatusOfOrder> getAllStatus() {
        String sql = "SELECT * FROM status_of_order";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(StatusOfOrder.class));
    }
}
