package br.com.trainer.trainerapi.model.repository;

import br.com.trainer.trainerapi.model.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
    Optional<Trainer> findByEmail(String email);
}
