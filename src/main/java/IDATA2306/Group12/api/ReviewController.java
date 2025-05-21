package IDATA2306.Group12.api;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import IDATA2306.Group12.dto.review.ReviewCreateDTO;
import IDATA2306.Group12.dto.review.ReviewResponseDTO;
import IDATA2306.Group12.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
@Tag(name = "Review Management", description = "APIs for managing reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Operation(
            summary = "Get a list of all reviews",
            description = "Get a list of all reviews."
    )
    @GetMapping("/admin/all")
    @ApiResponse(responseCode = "200", description = "List of all reviews")
    public List<ReviewResponseDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @Operation(
            summary = "Create a review",
            description = "Create a new review using a valid ReviewCreateDTO."
    )
    @PostMapping("/account/writeReview")
    @ApiResponse(responseCode = "201", description = "Review created successfully.")
    @ApiResponse(responseCode = "400", description = "Invalid input.")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponseDTO createReview(@Valid @RequestBody ReviewCreateDTO createDTO) {
        return reviewService.createReview(createDTO);
    }
}