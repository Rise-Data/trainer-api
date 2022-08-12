package br.com.trainer.trainerapi.repository;

import br.com.trainer.trainerapi.entity.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingTypeRepository extends JpaRepository<TrainingType, Integer> {
}
