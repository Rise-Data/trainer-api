package br.com.trainer.trainerapi.service;

import br.com.trainer.trainerapi.exception.RowNotFoundException;
import br.com.trainer.trainerapi.model.dto.exercise.ExerciseInputDto;
import br.com.trainer.trainerapi.model.dto.exercise.ExerciseResultDto;
import br.com.trainer.trainerapi.model.dto.exercise.ExerciseUpdateInputDto;
import br.com.trainer.trainerapi.model.entity.Exercise;
import br.com.trainer.trainerapi.model.repository.ExerciseRepository;
import br.com.trainer.trainerapi.model.repository.TrainingRepository;
import br.com.trainer.trainerapi.model.repository.ExerciseTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseTypeRepository exerciseTypeRepository;
    private final TrainingRepository trainingRepository;

    public ExerciseService(ExerciseRepository exerciseRepository, ExerciseTypeRepository exerciseTypeRepository, TrainingRepository trainingRepository) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseTypeRepository = exerciseTypeRepository;
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
                        m.getExerciseType().getId(),
                        m.getDescription(),
                        m.getLinkVideo())).toList());
    }

    public Page<ExerciseResultDto> listByTraining(Pageable pageable, Integer trainingId) throws RowNotFoundException {
        var training = trainingRepository.findById(trainingId).orElseThrow(() -> new RowNotFoundException("Training not found"));
        return new PageImpl<ExerciseResultDto>(exerciseRepository.findByTraining(training)
                .stream()
                .map(e -> new ExerciseResultDto(
                        e.getId(),
                        e.getName(),
                        e.getRepetitions(),
                        e.getTraining().getId(),
                        e.getExerciseType().getId(),
                        e.getDescription(),
                        e.getLinkVideo())).toList());
    }

    public Page<ExerciseResultDto> listByExerciseType(Pageable pageable, Integer exerciseTypeId) throws RowNotFoundException {
        var exerciseType = exerciseTypeRepository.findById(exerciseTypeId).orElseThrow(() -> new RowNotFoundException("ExerciseType not found"));
        return new PageImpl<ExerciseResultDto>(exerciseRepository.findByExerciseType(exerciseType)
                .stream()
                .map(e -> new ExerciseResultDto(
                        e.getId(),
                        e.getName(),
                        e.getRepetitions(),
                        e.getTraining().getId(),
                        e.getExerciseType().getId(),
                        e.getDescription(),
                        e.getLinkVideo())).toList());
    }

    public void addExercise(ExerciseInputDto exerciseInput) throws RowNotFoundException {
        if (exerciseInput == null)
            throw new NullPointerException("exercise can't be null");

        if (exerciseInput.exerciseType() == null)
            throw new NullPointerException("exerciseType can't be null");

        if (exerciseInput.name() == null)
            throw new NullPointerException("Name can't be null");

        if (exerciseInput.videoLink() == null)
            throw new NullPointerException("VideLink can't be null");

        if (exerciseInput.description() == null)
            throw new NullPointerException("Description can't be null");

        if (exerciseInput.repetitions() == null)
            throw new NullPointerException("Repetitions can't be null");

        var trainingType = exerciseTypeRepository.findById(exerciseInput.exerciseType()).orElseThrow(() -> new RowNotFoundException("exerciseType not found"));
        var training = trainingRepository.findById(exerciseInput.training()).orElseThrow(() -> new RowNotFoundException("Training not found"));
        var exercise = new Exercise(
                exerciseInput.repetitions(),
                exerciseInput.duration(),
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

        if (exerciseInput.exerciseType() == null)
            throw new NullPointerException("exerciseType can't be null");

        var exerciseType = exerciseTypeRepository.findById(exerciseInput.exerciseType()).orElseThrow(() -> new RowNotFoundException("exerciseType not found"));
        var exercise = exerciseRepository.findById(id).orElseThrow(() -> new RowNotFoundException("Exercise not found"));
        exercise.setName(exerciseInput.name());
        exercise.setDescription(exerciseInput.description());
        exercise.setLinkVideo(exerciseInput.videoLink());
        exercise.setRepetitions(exerciseInput.repetitions());
        exercise.setExerciseType(exerciseType);
        exercise.setDuration(exerciseInput.duration());
        exerciseRepository.save(exercise);
    }

    public void deleteExercise(Integer id) throws RowNotFoundException {
        var exercise = exerciseRepository.findById(id).orElseThrow(() -> new RowNotFoundException("Exercise not found"));
        exerciseRepository.delete(exercise);
    }
}
