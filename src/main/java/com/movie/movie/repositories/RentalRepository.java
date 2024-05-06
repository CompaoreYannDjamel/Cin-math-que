package com.movie.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.movie.entities.RentalEntity;

public interface RentalRepository extends JpaRepository<RentalEntity, Long> {

}
