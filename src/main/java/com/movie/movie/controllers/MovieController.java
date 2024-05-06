package com.movie.movie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.movie.movie.entities.MovieEntity;
import com.movie.movie.services.movie.MovieService;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public String MoviesList(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "movie/movies";
    }

    @GetMapping("/movies/new")
    public String createMovieForm(Model model) {
        MovieEntity movie = new MovieEntity();
        model.addAttribute("movie", movie);
        return "movie/add_movie";
    }

    @PostMapping("/movies")
    public String saveMovie(@ModelAttribute("movie") MovieEntity movie) {
        movie.setAvailable(true);
        movieService.saveMovie(movie);
        return "redirect:/movies";
    }

    @GetMapping("/movies/edit/{id}")
    public String editMovieForm(@PathVariable Long id, Model model) {
        model.addAttribute("movie", movieService.getMovieById(id));
        return "movie/edit_movie";
    }

    @PostMapping("/movies/{id}")
    public String updateMovie(@PathVariable Long id, @ModelAttribute("movie") MovieEntity movie, Model model) {
        // Get movie from Database by Id
        MovieEntity existingMovie = movieService.getMovieById(id);
        existingMovie.setDescription(movie.getDescription());
        existingMovie.setGender(movie.getGender());
        existingMovie.setReleaseYear(movie.getReleaseYear());
        existingMovie.setRentalPrice(movie.getRentalPrice());
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setAvailable(movie.isAvailable() == true);

        movieService.updateMovie(existingMovie);
        return "redirect:/movies";
    }

    @GetMapping("/movies/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.deleteMovieById(id);
        return "redirect:/movies";
    }

    @GetMapping("/movies/rental/{id}")
    public String rentalMovie(@PathVariable Long id, Model model) {
        model.addAttribute("movie", movieService.getMovieById(id));
        return "movie/rental_movie";
    }

}
