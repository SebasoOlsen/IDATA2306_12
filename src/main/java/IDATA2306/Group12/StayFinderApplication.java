package IDATA2306.Group12;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.SessionCookieConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;

/**
 * Main entry point for the StayFinder Spring Boot application.
 * Configures and starts the application, and sets session cookie properties.
 */
@SpringBootApplication
public class StayFinderApplication {

	/**
	 * Starts the Spring Boot application.
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		try {
			SpringApplication.run(StayFinderApplication.class, args);
		}catch(Exception e){
			System.out.println("Error starting the application: " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			System.out.println("Application started successfully.");
		}
	}

	/**
	 * Configures the session cookie settings, such as the maximum age.
	 *
	 * @return a ServletContextInitializer that sets session cookie properties
	 */
	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return new ServletContextInitializer() {
			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
				// Set cookie lifetime to 30 days (in seconds)
				sessionCookieConfig.setMaxAge(60 * 60 * 24 * 30);
			}
		};
	}
}
