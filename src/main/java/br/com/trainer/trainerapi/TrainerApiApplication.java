package br.com.trainer.trainerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableCaching
public class TrainerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainerApiApplication.class, args);
	}

}
