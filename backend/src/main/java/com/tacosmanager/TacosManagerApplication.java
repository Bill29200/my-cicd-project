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
			// Vérifier si l'utilisateur existe déjà
			if (userRepository.findByEmail("bill@admin.fr").isEmpty()) {
				User userAdmin = new User();
				userAdmin.setNom("Bill");
				userAdmin.setPassword("admin");
				userAdmin.setRole(Role.ADMIN);
				userAdmin.setEmail("bill@admin.fr"); // IMPORTANT : email requis

				// Enregistrement en base de données
				userRepository.save(userAdmin);
				System.out.println("✅ Utilisateur admin créé avec succès !");
			}
		};

	}


}
