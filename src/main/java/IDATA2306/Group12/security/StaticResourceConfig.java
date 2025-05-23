package IDATA2306.Group12.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for serving static resources.
 * Maps requests to `/pictures/**` to resources located in `classpath:/static/pictures/`.
 */
@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {
    /**
     * Adds resource handlers for serving static resources.
     *
     * @param registry the ResourceHandlerRegistry to configure
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/pictures/**")
                .addResourceLocations("classpath:/static/pictures/");
    }
}
