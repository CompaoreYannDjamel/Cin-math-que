package com.movie.movie.services.rental;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.movie.entities.RentalEntity;
import com.movie.movie.repositories.RentalRepository;

@Service
public class RentalService implements RentalServiceInterface {

    @Autowired
    private RentalRepository rentalRepository;

    @Override
    public void deleteRentalById(Long id) {
        rentalRepository.deleteById(id);
    }

    @Override
    public List<RentalEntity> getAllRentals() {
        return rentalRepository.findAll();
    }

    @Override
    public RentalEntity getRentalById(Long id) {
        return rentalRepository.findById(id).get();
    }

    @Override
    public RentalEntity saveRental(RentalEntity rental) {
        return rentalRepository.save(rental);
    }

    @Override
    public RentalEntity updateRental(RentalEntity rental) {
        return rentalRepository.save(rental);
    }

}
