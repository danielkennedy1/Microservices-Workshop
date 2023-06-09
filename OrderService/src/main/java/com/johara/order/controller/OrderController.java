package com.johara.order.controller;

import com.johara.order.model.Order;
import com.johara.order.repository.OrderRepository;
import com.johara.order.service.OrderService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepository;
    private OrderService orderService;

    @Autowired
    public OrderController(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> maybeOrder = orderRepository.findById(id);
        return maybeOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        order.setOrderStatus(Order.OrderStatus.CREATED);
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long id) {
        Optional<Order> maybeOrder = orderRepository.findById(id);
        if (maybeOrder.isPresent()) {
            Order order = maybeOrder.get();
            order.setOrderStatus(Order.OrderStatus.CANCELLED);
            Order updatedOrder = orderService.updateOrder(order);
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Optional<Order> maybeExistingOrder = orderRepository.findById(id);
        if (maybeExistingOrder.isPresent()) {
            order.setId(id);
            Order updatedOrder = orderRepository.save(order);
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        Optional<Order> maybeExistingOrder = orderRepository.findById(id);
        if (maybeExistingOrder.isPresent()) {
            orderRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/cookies")
    public String getCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            StringBuilder cookieInfo = new StringBuilder();
            for (Cookie cookie : cookies) {
                cookieInfo.append("Name: ").append(cookie.getName());
                cookieInfo.append(", Value: ").append(cookie.getValue());
                cookieInfo.append(", Domain: ").append(cookie.getDomain());
                cookieInfo.append(", Path: ").append(cookie.getPath());
                cookieInfo.append(", Max-Age: ").append(cookie.getMaxAge());
                cookieInfo.append(", Secure: ").append(cookie.getSecure());
                cookieInfo.append(", HttpOnly: ").append(cookie.isHttpOnly());
                cookieInfo.append("<br>");
            }

            return cookieInfo.toString();
        }

        return "No cookies found.";
    }
}
