package com.example.apponlineshop.service;

import com.example.apponlineshop.entity.Payment;
import com.example.apponlineshop.payload.ApiResponse;
import com.example.apponlineshop.payload.DtoPayment;
import com.example.apponlineshop.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public ApiResponse createPayment(DtoPayment dtoPayment) {
        Payment payment = new Payment();
        addOrEditPayment(dtoPayment, payment);
        return new ApiResponse("successfully saved payment", true);
    }

    public DtoPayment getOnePayment(Payment payment) {
        return new DtoPayment(
                payment.getId(),
                payment.getUserId(),
                payment.getPaySum(),
                payment.getPayType()
        );
    }

    public List<DtoPayment> getPaymentList() {
        return paymentRepository.findAll().stream().map(this::getOnePayment).collect(Collectors.toList());
    }

    public ApiResponse editPayment(UUID id, DtoPayment dtoPayment) {
        Optional<Payment> paymentById = paymentRepository.findById(id);
        if (paymentById.isPresent()) {
            addOrEditPayment(dtoPayment, paymentById.get());
            return new  ApiResponse("successfully edit payment", true);
        }
        return new ApiResponse("payment not found", false);
    }

    public ApiResponse deletePayment(UUID id) {
        paymentRepository.deleteById(id);
        return new ApiResponse("successfully delete payment", true);
    }

    public void addOrEditPayment(DtoPayment dtoPayment, Payment payment) {
        payment.setUserId(dtoPayment.getUserId());//edit user id
        payment.setPaySum(dtoPayment.getPaySum());
        payment.setPayType(dtoPayment.getPayType());
        paymentRepository.save(payment);
    }





}
