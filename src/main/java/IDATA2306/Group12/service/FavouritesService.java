package IDATA2306.Group12.service;

import IDATA2306.Group12.dto.booking.BookingResponseDTO;
import IDATA2306.Group12.dto.favourite.FavouriteResponseDTO;
import IDATA2306.Group12.dto.listing.ListingResponseDTO;
import IDATA2306.Group12.dto.user.UserResponseDTO;
import IDATA2306.Group12.entity.Favourite;
import IDATA2306.Group12.entity.Listing;
import IDATA2306.Group12.entity.User;
import IDATA2306.Group12.mapper.BookingMapper;
import IDATA2306.Group12.mapper.FavouriteMapper;
import IDATA2306.Group12.repository.FavouritesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavouritesService {

    private final FavouritesRepository favouritesRepository;
    private final FavouriteMapper favouriteMapper;

    public FavouritesService(FavouritesRepository favouritesRepository, FavouriteMapper favouriteMapper) {
        this.favouritesRepository = favouritesRepository;
        this.favouriteMapper = favouriteMapper;
    }

    public List<Favourite> findByUserId(int userId) {
        return favouritesRepository.findByUserId(userId);
    }

    public List<FavouriteResponseDTO> getFavouritesByUserId(int id) {
        List<Favourite> favourites = favouritesRepository.findByUserId(id);
        System.out.println("Favourites123: " + favourites);
        return favourites.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private FavouriteResponseDTO convertToResponseDTO(Favourite favourite) {
        FavouriteResponseDTO dto = new FavouriteResponseDTO();
        // Convert the User entity to UserResponseDTO.
        dto.setUser(convertUserToResponseDTO(favourite.getUser()));
        // Convert the Listing entity to ListingResponseDTO.
        dto.setListing(convertListingToResponseDTO(favourite.getListing()));
        return dto;
    }

    private UserResponseDTO convertUserToResponseDTO(User user) {
        UserResponseDTO userDTO = new UserResponseDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        // Map any additional properties as needed.
        return userDTO;
    }

    private ListingResponseDTO convertListingToResponseDTO(Listing listing) {
        ListingResponseDTO listingDTO = new ListingResponseDTO();
        listingDTO.setId(listing.getId());
        // Map any additional properties as needed.
        return listingDTO;
    }

    public List<?> getFavouritesByUser(User user) {
        System.out.println("User email: " + user.getEmail());
        System.out.println("User first name: " + user.getFirstName());
        System.out.println("User last name: " + user.getLastName());
        System.out.println("User ID: " + user.getId());
        List<Favourite> favourites = favouritesRepository.findByUserId(user.getId());
        return favourites.stream()
                .map(favouriteMapper :: toResponseDTO)
                .collect(Collectors.toList());
    }

//
//    public Object getFavouriteById(Long aLong) {
//    }
}