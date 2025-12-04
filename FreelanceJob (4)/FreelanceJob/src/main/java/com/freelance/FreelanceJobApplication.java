package com.freelance;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.ApplicationRunner;
import com.freelance.repository.UserRepository;
import com.freelance.entities.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class FreelanceJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreelanceJobApplication.class, args);
	}
	
	@Bean //method level annotation - to declare a method returning java object
	 ModelMapper modelMapper()
	{
		ModelMapper mapper=new ModelMapper();
		//configure mapper - to transfer the matching props (name + data type)
		mapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.STRICT)
		//configure mapper - not to transfer nulls from src -> dest
		.setPropertyCondition(Conditions.isNotNull());
		return mapper;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// allow CORS for frontend dev servers and for all backend endpoints
				registry.addMapping("/**")
					.allowedOrigins("http://localhost:5173", "http://localhost:5174")
					.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
					.allowedHeaders("*")
					.allowCredentials(true);
			}
		};
	}

	@Bean
	public ApplicationRunner dataLoader(UserRepository userRepository) {
		return args -> {
			try {
				String adminEmail = "admin@freelance.local";
				if (userRepository.findByEmail(adminEmail).isEmpty()) {
					UserEntity admin = new UserEntity();
					admin.setName("Administrator");
					admin.setEmail(adminEmail);
					// default password: Admin@123 (encoded)
					BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
					admin.setPassword(enc.encode("Admin@123"));
					admin.setRole("admin");
					userRepository.save(admin);
					System.out.println("[DATA_LOADER] Admin user created: " + adminEmail + " / Admin@123");
				} else {
					System.out.println("[DATA_LOADER] Admin user already exists");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		};
	}

}
