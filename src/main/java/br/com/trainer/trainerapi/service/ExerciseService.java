package br.com.trainer.trainerapi.service;

import br.com.trainer.trainerapi.exception.RowNotFoundException;
import br.com.trainer.trainerapi.model.dto.ExerciseInputDto;
import br.com.trainer.trainerapi.model.dto.ExerciseResultDto;
import br.com.trainer.trainerapi.model.entity.Exercise;
import br.com.trainer.trainerapi.model.entity.TrainingType;
import br.com.trainer.trainerapi.model.repository.ExerciseRepository;

import br.com.trainer.trainerapi.model.repository.TrainingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private TrainingTypeRepository trainingTypeRepository;

    public List<ExerciseResultDto> listAllExercises() {
        List<Exercise> exercise = exerciseRepository.findAll();
        return exercise
                .stream()
                .map(m -> new ExerciseResultDto(
                        m.getId(),
                        m.getName(),
                        m.getRepetitions(),
                        m.getTraining().getId(),
                        m.getTrainingType().getId(),
                        m.getDescription(),
                        m.getLinkVideo())).toList();
    }

    public void addExercise(ExerciseInputDto exercise) throws RowNotFoundException {
        if (exercise == null) {
            throw new RowNotFoundException("exercise can't be null");
        }

        if (exercise.trainingTypeId() == null) {
            throw new RowNotFoundException("trainingTypeId can't be null");
        }

        TrainingType trainingType;

        Exercise exerciseEntity = new Exercise();
        exerciseRepository.save(exerciseEntity);
    }

    public void updateExercise(ExerciseInputDto exercise, Integer id) throws RowNotFoundException {
        if (exercise == null) {
            throw new NullPointerException("exercise can't be null");
        }
        if (exerciseRepository.findById(id).isPresent()) {
            Exercise exerciseEntity = exerciseRepository.findById(id).get();
            exerciseEntity.setName(exercise.name());
            exerciseEntity.setDescription(exercise.description());
            exerciseEntity.setLinkVideo(exercise.videoLink());
            exerciseEntity.setRepetitions(exercise.repetitions());
            exerciseRepository.save(exerciseEntity);
        } else {
            throw new RowNotFoundException("exercise not found");
        }
    }

    public void deleteExercise(Integer id) throws RowNotFoundException {
        if (exerciseRepository.findById(id).isPresent()) {
            exerciseRepository.deleteById(id);
        } else {
            throw new RowNotFoundException("Exercise not found");
        }
    }
}
