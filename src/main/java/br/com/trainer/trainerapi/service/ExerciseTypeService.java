package br.com.trainer.trainerapi.service;

import br.com.trainer.trainerapi.exception.RowNotFoundException;
import br.com.trainer.trainerapi.model.dto.exerciseType.ExerciseTypeInput;
import br.com.trainer.trainerapi.model.dto.exerciseType.ExerciseTypeResultDto;
import br.com.trainer.trainerapi.model.entity.ExerciseType;
import br.com.trainer.trainerapi.model.repository.ExerciseTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExerciseTypeService {
    private final ExerciseTypeRepository exerciseTypeRepository;

    public ExerciseTypeService(ExerciseTypeRepository exerciseTypeRepository) {
        this.exerciseTypeRepository = exerciseTypeRepository;
    }

    public Page<ExerciseTypeResultDto> listAllTrainingTypes(Pageable pageable) {
        return new PageImpl<ExerciseTypeResultDto>(
                exerciseTypeRepository.findAll()
                        .stream()
                        .map(t -> new ExerciseTypeResultDto(
                                t.getId(),
                                t.getName()
                        )).toList()
        );
    }

    public ExerciseTypeResultDto listTrainingType(Integer id) throws RowNotFoundException {
        var exerciseType = exerciseTypeRepository.findById(id).orElseThrow(() -> new RowNotFoundException("exerciseType not found+"));
        return new ExerciseTypeResultDto(exerciseType.getId(), exerciseType.getName());
    }

    public void addTrainingType(ExerciseTypeInput exerciseTypeInput) {
        if (exerciseTypeInput == null)
            throw new NullPointerException("Input can't be null");

        if (exerciseTypeInput.name() == null || exerciseTypeInput.name().isEmpty())
            throw new NullPointerException("Name can't be null or empty");

        var exerciseType = new ExerciseType(exerciseTypeInput.name());
        exerciseTypeRepository.save(exerciseType);
    }

    public void updateTrainingType(ExerciseTypeInput exerciseTypeInput, Integer id) throws RowNotFoundException {

        if (exerciseTypeInput == null)
            throw new NullPointerException("Input can't be null");

        if (exerciseTypeInput.name() == null || exerciseTypeInput.name().isEmpty())
            throw new NullPointerException("Name can't be null or empty");

        var exerciseType = exerciseTypeRepository.findById(id).orElseThrow(() -> new RowNotFoundException("exerciseType not found"));
        exerciseType.setName(exerciseTypeInput.name());
        exerciseTypeRepository.save(exerciseType);
    }

    public void deleteTrainingType(Integer id) throws RowNotFoundException {
        var exerciseType = exerciseTypeRepository.findById(id).orElseThrow(() -> new RowNotFoundException("exerciseType not found"));
        exerciseTypeRepository.delete(exerciseType);
    }
}
