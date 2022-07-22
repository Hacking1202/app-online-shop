package com.example.apponlineshop.service;

import com.example.apponlineshop.entity.Order;
import com.example.apponlineshop.entity.Payment;
import com.example.apponlineshop.payload.ApiResponse;
import com.example.apponlineshop.payload.ReqOrder;
import com.example.apponlineshop.payload.ResOrder;
import com.example.apponlineshop.repository.OrderRepository;
import com.example.apponlineshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    PaymentRepository paymentRepository;
//    final OrderRepository orderRepository;
//    final PaymentRepository paymentRepository;
//
//    public OrderService(OrderRepository orderRepository, PaymentRepository paymentRepository) {
//        this.orderRepository = orderRepository;
//        this.paymentRepository = paymentRepository;
//    }

    public ApiResponse addOrder(ReqOrder reqOrder) {
        Optional<Payment> byId = paymentRepository.findById(reqOrder.getPaymentId());
        if (byId.isPresent()) {
            Order order = new Order();
            addOrEdit(reqOrder, order, byId.get());
            return new ApiResponse("successfully saved order", true);
        }
        return new ApiResponse("payment not fount", false);
    }

    public ResOrder getOneOrder(Order order) {
        return new ResOrder(order.getId(), order.getPayment().getId(), order.getShoppingDate(), order.isDelivered());
    }

    public List<ResOrder> getOrderList() {
        return orderRepository.findAll().stream().map(this::getOneOrder).collect(Collectors.toList());
    }

    public ApiResponse editOrder(UUID id, ReqOrder reqOrder) {
        Optional<Order> orderById = orderRepository.findById(id);
        if (orderById.isPresent()) {
            Optional<Payment> paymentById = paymentRepository.findById(reqOrder.getPaymentId());
            if (paymentById.isPresent()) {
                addOrEdit(reqOrder, orderById.get(), paymentById.get());
                return new ApiResponse("successfully edit order", true);
            }
            return new ApiResponse("payment not found", false);
        }
        return new ApiResponse("order not found", false);
    }

    public ApiResponse deleteOrder(UUID id) {
        paymentRepository.deleteById(id);
        return new ApiResponse("successfully delete order", true);
    }

    public void addOrEdit(ReqOrder reqOrder, Order order, Payment payment) {
        order.setPayment(payment);
        order.setDelivered(reqOrder.isDelivered());
        order.setShoppingDate(reqOrder.getShoppingDate());
        orderRepository.save(order);
    }
}
