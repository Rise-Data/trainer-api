package br.com.trainer.trainerapi.model.repository;

import br.com.trainer.trainerapi.model.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TrainingRepository extends JpaRepository<Training, Integer> {
}
