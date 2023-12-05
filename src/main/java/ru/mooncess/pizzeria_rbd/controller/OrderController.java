package ru.mooncess.pizzeria_rbd.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.pizzeria_rbd.dto.OrdersCreateDto;
import ru.mooncess.pizzeria_rbd.dto.OrdersDto;
import ru.mooncess.pizzeria_rbd.entity.Orders;
import ru.mooncess.pizzeria_rbd.service.ClientService;
import ru.mooncess.pizzeria_rbd.service.DishService;
import ru.mooncess.pizzeria_rbd.service.OrderService;
import ru.mooncess.pizzeria_rbd.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final ClientService clientService;
    private final DishService dishService;

    public OrderController(OrderService orderService, UserService userService, ClientService clientService, DishService dishService) {
        this.orderService = orderService;
        this.userService = userService;
        this.clientService = clientService;
        this.dishService = dishService;
    }
    @GetMapping
    public String getAllOrder(Model model){
        model.addAttribute("orders", orderService.getAllOrders());
        return "all_order";
    }
    @GetMapping("/my_order")
    public String getMyOrder(Model model, Authentication authentication){
        Long userId = userService.loadIdByUsername(authentication.getName());
        model.addAttribute("orders", orderService.getAllOrdersByClientId(clientService.getClientByUserId(userId).idClient));
        return "my_order";
    }
    @GetMapping("byId")
    public String getOrderById(@RequestParam Integer id, Model model){
        model.addAttribute("oneOrder", orderService.getOrderById(id));
        return "order";
    }

    @GetMapping("byId/{id}")
    public String getOrderById(@PathVariable int id, Model model){
        model.addAttribute("oneOrder", orderService.getOrderById(id));
        return "order";
    }

    @PostMapping("/create")
    public String createOrder(@RequestParam ("description") String description,
                              @RequestParam ("selectedDishes") List<Integer> selectedDishes,
                              Authentication authentication){
        Long userId = userService.loadIdByUsername(authentication.getName());
        OrdersCreateDto order = new OrdersCreateDto();
        order.clientIdClient = clientService.getClientByUserId(userId).idClient;
        order.description = description;
        order.totalCost = dishService.calcTotalCost(selectedDishes);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = dateFormat.format(new Date());
        order.dateOfCreation = currentDate;
        orderService.createOrder(order);
        clientService.updateNumberOrders(order.clientIdClient);
        return "redirect:/order/my_order";
    }
    @GetMapping("/update/{id}")
    public String getUpdateOrder(@PathVariable Integer id, Model model){
        OrdersDto order = orderService.getOrderById(id);
        order.setIdOrder(id);
        model.addAttribute("oneOrder", order);
        model.addAttribute("statuses", orderService.getAllStatus());
        return "update_order";
    }
    @PostMapping("/update/{id}")
    public String updateOrder(@PathVariable Integer id, @RequestParam ("newStatus") int idNewStatus){
        OrdersCreateDto order = new OrdersCreateDto();
        order.setIdOrder(id);
        order.setStatus(idNewStatus);
        orderService.updateOrder(order);
        return "redirect:/order/byId/" + id;
    }
    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Integer id){
        orderService.deleteOrderById(id);
        return "redirect:/order";
    }
}
