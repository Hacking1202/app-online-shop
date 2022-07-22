package com.example.apponlineshop.controller;

import com.example.apponlineshop.entity.Payment;
import com.example.apponlineshop.payload.ApiResponse;
import com.example.apponlineshop.payload.DtoPayment;
import com.example.apponlineshop.repository.PaymentRepository;
import com.example.apponlineshop.service.PaymentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/payment")
@CrossOrigin
public class PaymentController {

    final PaymentService paymentService;
    final PaymentRepository paymentRepository;

    public PaymentController(PaymentService paymentService, PaymentRepository paymentRepository) {
        this.paymentService = paymentService;
        this.paymentRepository = paymentRepository;
    }

    @PostMapping
    @ResponseBody
    public HttpEntity<?> addPayment(DtoPayment dtoPayment) {
        ApiResponse apiResponse = paymentService.createPayment(dtoPayment);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public HttpEntity<?> getOnePayment(@PathVariable UUID id) {
        return ResponseEntity.ok(paymentService.getOnePayment(paymentRepository.findById(id).orElseThrow(() -> new ResourceAccessException("getPayment"))));
    }

    @GetMapping
    @ResponseBody
    public List<DtoPayment> getPaymentList() {
        return paymentService.getPaymentList();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public HttpEntity<?> editPayment(@PathVariable UUID id, @RequestBody DtoPayment dtoPayment) {
        ApiResponse apiResponse = paymentService.editPayment(id, dtoPayment);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public HttpEntity<?> deletePayment(@PathVariable UUID id) {
        return ResponseEntity.ok(paymentService.deletePayment(id));
    }
}
