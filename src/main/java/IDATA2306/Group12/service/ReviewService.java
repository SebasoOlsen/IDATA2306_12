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

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewResponseDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();

        return reviews.stream().map(review -> new ReviewResponseDTO(
                review.getId(),
                review.getHotel().getId(),
                review.getUser().getId(),
                review.getReview(),
                review.getStars(),
                review.getPostDate()
        )).collect(Collectors.toList());
    }

    public ReviewResponseDTO createReview(ReviewCreateDTO createDTO) {
        Review review = new Review();
        review.getHotel().setId(createDTO.getHotelId());
        review.getUser().setId(createDTO.getUserId());
        review.setReview(createDTO.getReview());
        review.setStars(createDTO.getStars());
        review.setPostDate(LocalDate.now());

        Review savedReview = reviewRepository.save(review);

        return new ReviewResponseDTO(
                savedReview.getId(),
                savedReview.getHotel().getId(),
                savedReview.getUser().getId(),
                savedReview.getReview(),
                savedReview.getStars(),
                savedReview.getPostDate()
        );
    }
}