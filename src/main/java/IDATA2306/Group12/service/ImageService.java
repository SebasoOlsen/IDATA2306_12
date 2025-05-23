package IDATA2306.Group12.service;

import IDATA2306.Group12.entity.Image;
import IDATA2306.Group12.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

/**
 * Service class for managing image files and their metadata.
 * Handles uploading, retrieving, and deleting images associated with entities.
 */
@Service
public class ImageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${server.base-url}")
    private String serverBaseUrl;

    private final ImageRepository imageRepository;

    /**
     * Constructor for ImageService.
     *
     * @param imageRepository the repository for image metadata
     */
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    /**
     * Uploads an image file and saves its metadata.
     *
     * @param file   the image file to upload
     * @param type   the type of entity the image is associated with (e.g., "PRODUCT", "USER")
     * @param typeId the ID of the entity the image is associated with
     * @throws IOException if an error occurs while saving the file
     */
    public void uploadImage(MultipartFile file, String type, String typeId) throws IOException {
        String extension = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf('.') + 1);
        String fileName = type.toUpperCase() + "_" + typeId + "_" + UUID.randomUUID() + "." + extension;
        Path filePath = Paths.get(uploadDir, fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        Image image = new Image();
        image.setType(type.toUpperCase());
        image.setOwnerId(typeId);
        image.setUrl("/images/" + fileName);

        imageRepository.save(image);
    }

    /**
     * Retrieves a list of image URLs associated with a specific type and ID.
     *
     * @param type   the type of entity the images are associated with
     * @param typeId the ID of the entity the images are associated with
     * @return a list of image URLs
     */
    public List<String> getImageUrlsByTypeAndId(String type, String typeId) {
        List<Image> images = imageRepository.findByTypeAndTypeId(type.toUpperCase(), typeId);
        System.out.println("Image type: " + type);
        System.out.println("Image typeId: " + typeId);
        return images.stream()
                .map(image -> serverBaseUrl + image.getUrl()) // prepend full server URL
                .toList();
    }

    /**
     * Deletes an image file and its metadata.
     *
     * @param id the ID of the image to delete
     */
    public void deleteImage(int id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found"));

        try {
            String filename = image.getUrl().replace("/images/", "");
            Files.deleteIfExists(Paths.get(uploadDir, filename));
            imageRepository.delete(image);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting image file");
        }
    }
}
