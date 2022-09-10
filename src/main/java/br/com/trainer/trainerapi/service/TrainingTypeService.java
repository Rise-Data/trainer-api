package br.com.trainer.trainerapi.service;

import br.com.trainer.trainerapi.exception.RowNotFoundException;
import br.com.trainer.trainerapi.model.dto.trainingType.TrainingTypeInput;
import br.com.trainer.trainerapi.model.dto.trainingType.TrainingTypeResultDto;
import br.com.trainer.trainerapi.model.entity.TrainingType;
import br.com.trainer.trainerapi.model.repository.TrainingTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TrainingTypeService {
    private final TrainingTypeRepository trainingTypeRepository;

    public TrainingTypeService(TrainingTypeRepository trainingTypeRepository) {
        this.trainingTypeRepository = trainingTypeRepository;
    }

    public Page<TrainingTypeResultDto> listAllTrainingTypes(Pageable pageable) {
        return new PageImpl<TrainingTypeResultDto>(
                trainingTypeRepository.findAll()
                        .stream()
                        .map(t -> new TrainingTypeResultDto(
                                t.getId(),
                                t.getName(),
                                t.getExercise().getId()
                        )).toList()
        );
    }

    public TrainingTypeResultDto listTrainingType(Integer id) throws RowNotFoundException {
        var trainingType = trainingTypeRepository.findById(id).orElseThrow(() -> new RowNotFoundException("TrainingType not found+"));
        return new TrainingTypeResultDto(trainingType.getId(), trainingType.getName(), trainingType.getExercise().getId());
    }

    public void addTrainingType(TrainingTypeInput trainingTypeInput) {
        if (trainingTypeInput == null)
            throw new NullPointerException("Input can't be null");

        if (trainingTypeInput.name() == null || trainingTypeInput.name().isEmpty())
            throw new NullPointerException("Name can't be null or empty");

        var trainingType = new TrainingType(trainingTypeInput.name());
        trainingTypeRepository.save(trainingType);
    }

    public void updateTrainingType(TrainingTypeInput trainingTypeInput, Integer id) throws RowNotFoundException {

        if (trainingTypeInput == null)
            throw new NullPointerException("Input can't be null");

        if (trainingTypeInput.name() == null || trainingTypeInput.name().isEmpty())
            throw new NullPointerException("Name can't be null or empty");

        var trainingType = trainingTypeRepository.findById(id).orElseThrow(() -> new RowNotFoundException("TrainingType not found"));
        trainingType.setName(trainingTypeInput.name());
        trainingTypeRepository.save(trainingType);
    }

    public void deleteTrainingType(Integer id) throws RowNotFoundException {
        var trainingType = trainingTypeRepository.findById(id).orElseThrow(() -> new RowNotFoundException("TrainingType not found"));
        trainingTypeRepository.delete(trainingType);
    }
}
