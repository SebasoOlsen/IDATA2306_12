package IDATA2306.Group12.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * Configuration class for customizing Spring MVC settings.
 * <p>
 * This class configures resource handlers to serve static files (such as images)
 * from a directory specified by the {@code file.upload-dir} property.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * The directory where uploaded files are stored.
     * Injected from the {@code file.upload-dir} property in the application configuration.
     */
    @Value("${file.upload-dir}")
    private String uploadDir;
    /**
     * Adds resource handlers for serving static resources.
     * <p>
     * Maps requests to {@code /images/**} to files in the upload directory on the file system.
     *
     * @param registry the resource handler registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        String resourceLocation = "file:" + uploadPath.toString() + "/";

        registry.addResourceHandler("/images/**")
                .addResourceLocations(resourceLocation);
    }
}
