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

@Service
public class ImageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${server.base-url}")
    private String serverBaseUrl;

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

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

    public List<String> getImageUrlsByTypeAndId(String type, String typeId) {
        List<Image> images = imageRepository.findByTypeAndTypeId(type.toUpperCase(), typeId);
        return images.stream()
                .map(image -> serverBaseUrl + image.getUrl()) // prepend full server URL
                .toList();
    }

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
