package IDATA2306.Group12.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import IDATA2306.Group12.dto.review.ReviewCreateDTO;
import IDATA2306.Group12.dto.review.ReviewResponseDTO;
import IDATA2306.Group12.entity.Review;
import IDATA2306.Group12.repository.ReviewRepository;

/**
 * Service for managing reviews.
 * Handles creation and retrieval of hotel reviews.
 */
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    /**
     * Constructs a ReviewService with the required repository.
     *
     * @param reviewRepository the review repository
     */
    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Retrieves all reviews.
     *
     * @return a list of ReviewResponseDTOs
     */
    public List<ReviewResponseDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();

        return reviews.stream().map(review -> new ReviewResponseDTO(
                review.getId(),
                review.getHotel().getId(),
                review.getUser().getId(),
                review.getReview(),
                review.getStars(),
                review.getPostDate())).collect(Collectors.toList());
    }

    /**
     * Creates a new review.
     *
     * @param createDTO the review creation DTO
     * @return the created ReviewResponseDTO
     */
    public ReviewResponseDTO createReview(ReviewCreateDTO createDTO) {
        Review review = new Review();
        review.getHotel().setId(createDTO.getHotelId());
        review.getUser().setId(createDTO.getUserId());
        review.setReview(createDTO.getReview());
        review.setStars(createDTO.getStars());
        review.setPostDate(LocalDate.now());

        Review savedReview = reviewRepository.save(review);

        // TODO: add update average review.

        return new ReviewResponseDTO(
                savedReview.getId(),
                savedReview.getHotel().getId(),
                savedReview.getUser().getId(),
                savedReview.getReview(),
                savedReview.getStars(),
                savedReview.getPostDate());
    }

}