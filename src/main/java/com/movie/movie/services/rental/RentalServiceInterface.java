package com.movie.movie.services.rental;

import java.util.List;

import com.movie.movie.entities.RentalEntity;

public interface RentalServiceInterface {
    List<RentalEntity> getAllRentals();

    RentalEntity saveRental(RentalEntity rental);

    RentalEntity getRentalById(Long id);

    RentalEntity updateRental(RentalEntity rental);

    void deleteRentalById(Long id);
}
