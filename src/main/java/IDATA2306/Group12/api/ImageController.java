package IDATA2306.Group12.api;

import IDATA2306.Group12.entity.Image;
import IDATA2306.Group12.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") String type,
            @RequestParam("typeId") String typeId) throws IOException {
        imageService.uploadImage(file, type, typeId);
        return ResponseEntity.ok("Image uploaded");
    }

    @GetMapping("/urls")
    public ResponseEntity<List<String>> getImageUrls(@RequestParam String type, @RequestParam String typeId) {
        return ResponseEntity.ok(imageService.getImageUrlsByTypeAndId(type, typeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable int id) {
        imageService.deleteImage(id);
        return ResponseEntity.ok("Image deleted");
    }
}
