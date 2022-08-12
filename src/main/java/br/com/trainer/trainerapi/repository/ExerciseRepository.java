package br.com.trainer.trainerapi.repository;

import br.com.trainer.trainerapi.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
}
