package com.movie.movie.services.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.movie.entities.MovieEntity;
import com.movie.movie.repositories.MovieRepository;

@Service
public class MovieService implements MovieServiceInterface {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<MovieEntity> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public MovieEntity getMovieById(Long id) {
        return movieRepository.findById(id).get();
    }

    @Override
    public MovieEntity saveMovie(MovieEntity movie) {
        return movieRepository.save(movie);
    }

    @Override
    public MovieEntity updateMovie(MovieEntity movie) {
        return movieRepository.save(movie);
    }

}
