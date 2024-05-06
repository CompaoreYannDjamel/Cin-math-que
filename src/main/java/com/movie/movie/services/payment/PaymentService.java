package com.movie.movie.services.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.movie.entities.PaymentEntity;
import com.movie.movie.repositories.PaymentRepository;

@Service
public class PaymentService implements PaymentServiceInterface {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public void deletePaymentById(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public List<PaymentEntity> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public PaymentEntity getPaymentById(Long id) {
        return paymentRepository.findById(id).get();
    }

    @Override
    public PaymentEntity savePayment(PaymentEntity payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public PaymentEntity updatePayment(PaymentEntity payment) {
        return paymentRepository.save(payment);
    }

}
