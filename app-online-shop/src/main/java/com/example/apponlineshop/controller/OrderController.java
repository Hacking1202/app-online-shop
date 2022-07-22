package com.example.apponlineshop.controller;

import com.example.apponlineshop.entity.Order;
import com.example.apponlineshop.payload.ApiResponse;
import com.example.apponlineshop.payload.ReqOrder;
import com.example.apponlineshop.payload.ResOrder;
import com.example.apponlineshop.repository.OrderRepository;
import com.example.apponlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
@CrossOrigin
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;

//    final OrderRepository orderRepository;
//    final OrderService orderService;
//
//    public OrderController(OrderRepository orderRepository, OrderService orderService) {
//        this.orderRepository = orderRepository;
//        this.orderService = orderService;
//    }

    @PostMapping
    @ResponseBody
    public HttpEntity<?> createOrder(@RequestBody ReqOrder reqOrder) {
        ApiResponse apiResponse = orderService.addOrder(reqOrder);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public HttpEntity<?> getOneOrder(@PathVariable UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceAccessException("getOrder"));
        return ResponseEntity.ok(orderService.getOneOrder(order));
    }

    @GetMapping
    @ResponseBody
    public List<ResOrder> getOrder() {
        return orderService.getOrderList();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public HttpEntity<?> updateOrder(@PathVariable UUID id, @RequestBody ReqOrder reqOrder) {
        ApiResponse apiResponse = orderService.editOrder(id, reqOrder);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public HttpEntity<?> deleteOrder(@PathVariable UUID id) {
        ApiResponse apiResponse = orderService.deleteOrder(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200:409).body(apiResponse);
    }
}