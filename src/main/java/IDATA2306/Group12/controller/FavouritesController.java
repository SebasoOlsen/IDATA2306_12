package IDATA2306.Group12.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import IDATA2306.Group12.dto.booking.BookingResponseDTO;
import IDATA2306.Group12.dto.favourite.FavouriteResponseDTO;
import IDATA2306.Group12.entity.User;
import IDATA2306.Group12.repository.FavouritesRepository;
import IDATA2306.Group12.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IDATA2306.Group12.entity.Favourite;
import IDATA2306.Group12.service.FavouritesService;

@RestController
@RequestMapping("/favourites")
public class FavouritesController {

    private final FavouritesService favouriteService;
    private final FavouritesRepository favouritesRepository;
    private final UserRepository userRepository;

    public FavouritesController(FavouritesService favouriteService, FavouritesRepository favouritesRepository, UserRepository userRepository) {
        this.favouriteService = favouriteService;
        this.favouritesRepository = favouritesRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public ResponseEntity<List<?>>getUserFavourites() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }
        // Assuming the principal contains the user's email or username
        String username = auth.getName();
        System.out.println("Username: " + username);
        User user = userRepository.findByEmail(username);
        if (user == null) {
            System.out.println("User not found");
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(favouriteService.getFavouritesByUser(user));
    }
}