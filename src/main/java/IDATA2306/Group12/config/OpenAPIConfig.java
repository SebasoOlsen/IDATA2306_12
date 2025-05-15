package IDATA2306.Group12.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI StayfinderOpenAPI() {
        Contact contact = new Contact();
        contact.setName("Group 12");

        Info info = new Info()
                .title("StayFinder API")
                .version("1.0.0")
                .contact(contact)
                .description("StayFinder API for StayFinder Web App");

        return new OpenAPI()
                .openapi("3.1.0")
                .info(info)
                .tags(List.of(
                        new Tag().name("User Management").description("APIs for managing users.")
                ));
    }
}
