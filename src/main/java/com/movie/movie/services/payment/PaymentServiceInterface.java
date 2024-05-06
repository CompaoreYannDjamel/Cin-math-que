package com.movie.movie.services.payment;

import java.util.List;

import com.movie.movie.entities.PaymentEntity;

public interface PaymentServiceInterface {
    List<PaymentEntity> getAllPayments();

    PaymentEntity savePayment(PaymentEntity payment);

    PaymentEntity getPaymentById(Long id);

    PaymentEntity updatePayment(PaymentEntity payment);

    void deletePaymentById(Long id);
}
