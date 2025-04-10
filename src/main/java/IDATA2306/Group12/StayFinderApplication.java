package IDATA2306.Group12;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StayFinderApplication {

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
}
