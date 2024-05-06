package com.movie.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.movie.entities.MovieEntity;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

}
