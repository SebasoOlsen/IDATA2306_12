package IDATA2306.Group12.api;

import java.util.List;

import IDATA2306.Group12.entity.User;
import IDATA2306.Group12.repository.FavouritesRepository;
import IDATA2306.Group12.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IDATA2306.Group12.service.FavouritesService;

@RestController
@RequestMapping("/api/favourites")
@Tag(name = "Favourites Management", description = "APIs for managing favourites.")
public class FavouritesController {

    private final FavouritesService favouriteService;
    private final UserRepository userRepository;

    public FavouritesController(FavouritesService favouriteService, UserRepository userRepository) {
        this.favouriteService = favouriteService;
        this.userRepository = userRepository;
    }

    @Operation(
            summary = "Get user favourites",
            description = "Get a list of favourites connected to the user."
    )
    @ApiResponse(responseCode = "200", description = "List of favourites.")
    @ApiResponse(responseCode = "401", description = "User is not authenticated.")
    @ApiResponse(responseCode = "403", description = "User is not authorized to view this list of favourites.")
    @ApiResponse(responseCode = "404", description = "User not found.")
    @GetMapping("/account/user")
    public ResponseEntity<?>getUserFavourites() {

        //TODO: Put this logic in FavouritesService

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