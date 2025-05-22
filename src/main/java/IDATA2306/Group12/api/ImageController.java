package IDATA2306.Group12.api;

import IDATA2306.Group12.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Controller for handling image-related API requests.
 */
@RestController
@RequestMapping("/api/images")
@Tag(name = "Image Management", description = "APIs for managing images.")
public class ImageController {

    private final ImageService imageService;

    /**
     * Constructor for ImageController.
     * 
     * @param imageService the image service
     */
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Upload an image.
     * 
     * @param file   the image file to upload
     * @param type   the type of the image (e.g., hotel, room)
     * @param typeId the ID related to the type
     * @return status message
     */
    @Operation(summary = "Upload an image", description = "Upload an image using a MultipartFile and a type string.")
    @ApiResponse(responseCode = "200", description = "Image uploaded successfully.")
    @ApiResponse(responseCode = "400", description = "Invalid input.")
    @ApiResponse(responseCode = "403", description = "Not authorized to upload an image.")
    @PostMapping("/admin/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") String type,
            @RequestParam("typeId") String typeId) {
        try {
            imageService.uploadImage(file, type, typeId);
            return ResponseEntity.ok("Image uploaded");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Get image URLs by type and ID.
     * 
     * @param type   the type of the image (e.g., hotel, room)
     * @param typeId the ID related to the type
     * @return list of image URLs
     */
    @Operation(summary = "Get an image by type and ID", description = "Get an image by type and ID.")
    @ApiResponse(responseCode = "200", description = "Image with matching type and ID.")
    @GetMapping("/public/urls")
    public ResponseEntity<List<String>> getImageUrls(@RequestParam String type, @RequestParam String typeId) {
        return ResponseEntity.ok(imageService.getImageUrlsByTypeAndId(type, typeId));
    }
}
