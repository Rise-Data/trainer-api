package br.com.trainer.trainerapi.repository;

import br.com.trainer.trainerapi.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
}
