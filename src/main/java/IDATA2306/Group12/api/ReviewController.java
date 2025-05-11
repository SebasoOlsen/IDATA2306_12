package IDATA2306.Group12.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import IDATA2306.Group12.dto.review.ReviewCreateDTO;
import IDATA2306.Group12.dto.review.ReviewResponseDTO;
import IDATA2306.Group12.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewResponseDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponseDTO createReview(@RequestBody ReviewCreateDTO createDTO) {
        return reviewService.createReview(createDTO);
    }
}