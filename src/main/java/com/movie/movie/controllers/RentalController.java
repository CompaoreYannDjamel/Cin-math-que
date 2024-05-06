package com.movie.movie.controllers;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.movie.entities.ClientEntity;
import com.movie.movie.entities.MovieEntity;
import com.movie.movie.entities.PaymentEntity;
import com.movie.movie.entities.RentalEntity;
import com.movie.movie.services.client.ClientService;
import com.movie.movie.services.movie.MovieService;
import com.movie.movie.services.payment.PaymentService;
import com.movie.movie.services.rental.RentalService;

@Controller
@RequestMapping("/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private PaymentService paymentService;

    // Afficher le formulaire de location
    @GetMapping("/rent/{movieId}")
    public String rentMovieForm(@PathVariable Long movieId, Model model) {
        MovieEntity movie = movieService.getMovieById(movieId);
        model.addAttribute("movie", movie);
        model.addAttribute("rental", new RentalEntity()); // Créer une nouvelle location
        model.addAttribute("client", new ClientEntity()); // Créer un nouveau client
        return "rental/rent_movie";
    }

    // Enregistrer la location d'un film avec paiement
    @PostMapping("/rent")
    public String rentMovie(@ModelAttribute("rental") RentalEntity rental,
            @ModelAttribute("client") ClientEntity client,
            @RequestParam("movieId") Long movieId,
            @RequestParam("rentalPrice") BigDecimal rentalPrice) {

        // Calcul de la date de fin de location (7 jours après la date de location)
        // Date endDate = new Date(new Date().getTime() + (7 * 24 * 60 * 60 * 1000)); //
        // Ajouter 7 jours en millisecondes

        MovieEntity movie = movieService.getMovieById(movieId);

        // Enregistrer le client s'il n'existe pas déjà
        ClientEntity existingClient = clientService.getClientByEmail(client.getEmail());
        if (existingClient == null) {
            clientService.saveClient(client);
            existingClient = client; // Mettre à jour la référence au client existant après sauvegarde
        }

        // Effectuer le paiement
        PaymentEntity payment = new PaymentEntity();
        payment.setAmount(rentalPrice);
        payment.setPaymentDate(new Date());
        payment.setPaymentStatus("CONFIRMED"); // Statut de paiement confirmé directement lors de la location
        payment.setPaymentType("RENTAL"); // Type de paiement pour la location
        payment.setClient(existingClient); // Associer le paiement au client
        paymentService.savePayment(payment); // Enregistrer le paiement

        // Mettre à jour la location avec le paiement
        rental.setMovie(movie);
        rental.setRentalDate(new Date());
        rental.setEndDate(new Date());
        rental.setClient(existingClient);
        rental.setRentalStatus("ACTIVE"); // Statut de location actif après paiement
        rental.setPayment(payment); // Associer le paiement à la location
        rentalService.saveRental(rental); // Enregistrer la location

        // Rendre le film indisponible a la Location
        movie.setAvailable(false);
        movieService.saveMovie(movie);

        // Redirection vers une page de confirmation ou une autre vue appropriée
        return "redirect:/rentals/confirmation";
    }

    // Page de confirmation après la location
    @GetMapping("/confirmation")
    public String rentalConfirmation() {
        return "rental/confirmation";
    }

    @GetMapping("/list")
    public String getAllRentals(Model model) {
        model.addAttribute("rentals", rentalService.getAllRentals());
        return "rental/rental_list";
    }

    @GetMapping("/details/{id}")
    public String getRentalDetails(@PathVariable Long id, Model model) {
        RentalEntity rental = rentalService.getRentalById(id);
        if (rental == null) {
            // Gérer le cas où la location n'existe pas
            return "redirect:/rentals";
        }
        model.addAttribute("rental", rental);
        return "rental/details";
    }
}
