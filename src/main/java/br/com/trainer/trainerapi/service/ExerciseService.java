package br.com.trainer.trainerapi.service;

import br.com.trainer.trainerapi.exception.RowNotFoundException;
import br.com.trainer.trainerapi.model.dto.exercise.ExerciseInputDto;
import br.com.trainer.trainerapi.model.dto.exercise.ExerciseResultDto;
import br.com.trainer.trainerapi.model.dto.exercise.ExerciseUpdateInputDto;
import br.com.trainer.trainerapi.model.entity.Exercise;
import br.com.trainer.trainerapi.model.repository.ExerciseRepository;
import br.com.trainer.trainerapi.model.repository.TrainingRepository;
import br.com.trainer.trainerapi.model.repository.TrainingTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final TrainingTypeRepository trainingTypeRepository;
    private final TrainingRepository trainingRepository;

    public ExerciseService(ExerciseRepository exerciseRepository, TrainingTypeRepository trainingTypeRepository, TrainingRepository trainingRepository) {
        this.exerciseRepository = exerciseRepository;
        this.trainingTypeRepository = trainingTypeRepository;
        this.trainingRepository = trainingRepository;
    }

    public Page<ExerciseResultDto> listAllExercises(Pageable pageable) {
        return new PageImpl<ExerciseResultDto>(exerciseRepository.findAll(pageable)
                .stream()
                .map(m -> new ExerciseResultDto(
                        m.getId(),
                        m.getName(),
                        m.getRepetitions(),
                        m.getTraining().getId(),
                        m.getTrainingType().getId(),
                        m.getDescription(),
                        m.getLinkVideo())).toList());
    }

    public void addExercise(ExerciseInputDto exerciseInput) throws RowNotFoundException {
        if (exerciseInput == null)
            throw new NullPointerException("exercise can't be null");

        if (exerciseInput.trainingTypeId() == null)
            throw new NullPointerException("trainingTypeId can't be null");

        if (exerciseInput.name() == null)
            throw new NullPointerException("Name can't be null");

        if (exerciseInput.videoLink() == null)
            throw new NullPointerException("VideLink can't be null");

        if (exerciseInput.description() == null)
            throw new NullPointerException("Description can't be null");

        if (exerciseInput.repetitions() == null)
            throw new NullPointerException("Repetitions can't be null");

        var trainingType = trainingTypeRepository.findById(exerciseInput.trainingTypeId()).orElseThrow(() -> new RowNotFoundException("TrainingType not found"));
        var training = trainingRepository.findById(exerciseInput.training()).orElseThrow(() -> new RowNotFoundException("Training not found"));
        var exercise = new Exercise(
                exerciseInput.repetitions(),
                exerciseInput.description(),
                exerciseInput.videoLink(),
                exerciseInput.name(),
                training,
                trainingType);
        exerciseRepository.save(exercise);
    }

    public void updateExercise(ExerciseUpdateInputDto exerciseInput, Integer id) throws RowNotFoundException {
        if (exerciseInput == null)
            throw new NullPointerException("exercise can't be null");

        if (exerciseInput.name() == null)
            throw new NullPointerException("name can't be null");

        if (exerciseInput.repetitions() == null)
            throw new NullPointerException("repetitions can't be null");

        if (exerciseInput.videoLink() == null)
            throw new NullPointerException("videoLink can't be null");

        if (exerciseInput.description() == null)
            throw new NullPointerException("description can't be null");

        if (exerciseInput.trainingType() == null)
            throw new NullPointerException("trainingTypeId can't be null");

        var trainingType = trainingTypeRepository.findById(exerciseInput.trainingType()).orElseThrow(() -> new RowNotFoundException("TrainingType not found"));
        var exercise = exerciseRepository.findById(id).orElseThrow(() -> new RowNotFoundException("Exercise not found"));
        exercise.setName(exerciseInput.name());
        exercise.setDescription(exerciseInput.description());
        exercise.setLinkVideo(exerciseInput.videoLink());
        exercise.setRepetitions(exerciseInput.repetitions());
        exercise.setTrainingType(trainingType);
        exerciseRepository.save(exercise);
    }

    public void deleteExercise(Integer id) throws RowNotFoundException {
        var exercise = exerciseRepository.findById(id).orElseThrow(() -> new RowNotFoundException("Exercise not found"));
        exerciseRepository.delete(exercise);
    }
}
