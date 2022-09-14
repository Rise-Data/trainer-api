package br.com.trainer.trainerapi.model.repository;

import br.com.trainer.trainerapi.model.entity.ExerciseType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExerciseTypeRepository extends JpaRepository<ExerciseType, Integer> {
}
