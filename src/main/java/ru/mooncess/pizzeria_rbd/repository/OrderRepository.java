package ru.mooncess.pizzeria_rbd.repository;

import ru.mooncess.pizzeria_rbd.dto.OrdersCreateDto;
import ru.mooncess.pizzeria_rbd.dto.OrdersDto;
import ru.mooncess.pizzeria_rbd.entity.Orders;
import ru.mooncess.pizzeria_rbd.entity.StatusOfOrder;

import java.util.List;

public interface OrderRepository {
    List<OrdersDto> getAllOrder();
    List<OrdersDto> getAllOrderByClientId(int id);
    OrdersDto getOrderById(Integer id);
    void createOrder(OrdersCreateDto order);
    void updateOrder(OrdersCreateDto order);
    void deleteOrderById(Integer id);
    List<StatusOfOrder> getAllStatus();
}
