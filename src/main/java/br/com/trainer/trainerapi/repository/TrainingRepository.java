package br.com.trainer.trainerapi.repository;

import br.com.trainer.trainerapi.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Integer> {
}
