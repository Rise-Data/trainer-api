package br.com.trainer.trainerapi.model.repository;

import br.com.trainer.trainerapi.model.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
}
