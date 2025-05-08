package IDATA2306.Group12.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Injects the value from application.properties (e.g.,
    // file.upload-dir=/opt/uploads/)
    @Value("${file.upload-dir}")
    private String uploadDir;

    // This method is called by Spring at startup to map URLs to resource locations
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Maps /images/** URLs to files located in the uploadDir on the file system
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + uploadDir);
    }
}
