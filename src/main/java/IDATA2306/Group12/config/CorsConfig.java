package IDATA2306.Group12.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * Configuration class for setting up Cross-Origin Resource Sharing (CORS) in
 * the application.
 * This allows the backend to handle requests from a specified frontend origin.
 */
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*") // match ALL API paths
                        .allowedOriginPatterns(
                                "http://localhost:5173/",
                                "http://localhost:5174/",
                                "http://10.212.27.13/",
                                "http://group12.web-tek.ninja/")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("")
                        .allowCredentials(true);
            }
        };
    }
}
