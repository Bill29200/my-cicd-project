package com.tacosmanager;

import com.tacosmanager.entity.Role;
import com.tacosmanager.entity.User;
import com.tacosmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class TacosManagerApplication {

    public static void main(String[] args) {
		SpringApplication.run(TacosManagerApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository) {
		return args -> {
			User userAdmin = User.builder()
					.nom("Bill")
					.password("admin")
					.role(Role.ADMIN)
					.email("bill@admin.fr")
					.build();

			// Enregistrement en base de données
			userRepository.save(userAdmin);
		};
	}


}
