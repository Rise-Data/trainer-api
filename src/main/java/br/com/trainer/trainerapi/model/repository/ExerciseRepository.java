package br.com.trainer.trainerapi.model.repository;

import br.com.trainer.trainerapi.model.entity.Exercise;
import br.com.trainer.trainerapi.model.entity.Member;
import br.com.trainer.trainerapi.model.entity.Trainer;
import br.com.trainer.trainerapi.model.entity.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    List<Exercise> findByTrainingType(TrainingType trainingType);
}
