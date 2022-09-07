package br.com.trainer.trainerapi.model.repository;

import br.com.trainer.trainerapi.model.entity.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TrainingTypeRepository extends JpaRepository<TrainingType, Integer> {
}
