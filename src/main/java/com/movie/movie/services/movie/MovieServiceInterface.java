package com.movie.movie.services.movie;

import java.util.List;

import com.movie.movie.entities.MovieEntity;

public interface MovieServiceInterface {
    List<MovieEntity> getAllMovies();

    MovieEntity saveMovie(MovieEntity movie);

    MovieEntity getMovieById(Long id);

    MovieEntity updateMovie(MovieEntity movie);

    void deleteMovieById(Long id);
}
