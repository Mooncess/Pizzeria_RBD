package ru.mooncess.pizzeria_rbd.service;

import org.springframework.stereotype.Service;
import ru.mooncess.pizzeria_rbd.dto.OrdersCreateDto;
import ru.mooncess.pizzeria_rbd.dto.OrdersDto;
import ru.mooncess.pizzeria_rbd.entity.StatusOfOrder;
import ru.mooncess.pizzeria_rbd.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository OrderRepository, UserService userService) {
        this.orderRepository = OrderRepository;
    }
    public List<OrdersDto> getAllOrders(){
        return orderRepository.getAllOrder();
    }
    public List<OrdersDto> getAllOrdersByClientId(int idClient){
        return orderRepository.getAllOrderByClientId(idClient);
    }
    public OrdersDto getOrderById(int id){
        return orderRepository.getOrderById(id);
    }
    public void createOrder(OrdersCreateDto order){
        orderRepository.createOrder(order);
    }
    public void updateOrder(OrdersCreateDto Order){
        orderRepository.updateOrder(Order);
    }
    public void deleteOrderById(Integer id){
        orderRepository.deleteOrderById(id);
    }

    public List<StatusOfOrder> getAllStatus() {return orderRepository.getAllStatus();}
}
