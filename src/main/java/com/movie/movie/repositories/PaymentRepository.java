package com.movie.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.movie.entities.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

}
