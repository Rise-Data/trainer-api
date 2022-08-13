package br.com.trainer.trainerapi.service;

import br.com.trainer.trainerapi.exception.RowNotFoundException;
import br.com.trainer.trainerapi.model.dto.TrainerResultDto;
import br.com.trainer.trainerapi.model.entity.Trainer;
import br.com.trainer.trainerapi.model.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    public void registerTrainer(Trainer trainer) {
        if (trainer == null) {
            throw new NullPointerException("Trainer can't be null");
        }
        trainerRepository.save(trainer);
    }

    public List<Trainer> listAllTrainers() {
        return trainerRepository.findAll();
    }

    public void updateTrainer(Trainer trainer, Integer id) throws RowNotFoundException {
        if (trainer == null) {
            throw new NullPointerException("Trainer can't be null");
        }

        if (trainerRepository.findById(id).isEmpty()) {
            throw new RowNotFoundException("Trainer not found");
        }

        trainer.setId(id);
        trainerRepository.save(trainer);
    }

    public TrainerResultDto listTrainer(Integer id) throws RowNotFoundException {
        if (trainerRepository.findById(id).isPresent()) {
            Trainer trainerEntity = trainerRepository.findById(id).get();
            return new TrainerResultDto(
                    trainerEntity.getUser(),
                    trainerEntity.getEmail(),
                    trainerEntity.getPhone(),
                    trainerEntity.getMembers(),
                    trainerEntity.getChatbot()
            );
        }

        throw new RowNotFoundException("Trainer not found");
    }

    public void deleteTrainer(Integer id) throws RowNotFoundException {
        if (trainerRepository.findById(id).isEmpty()) {
            throw new RowNotFoundException("Trainer not found");
        }

        trainerRepository.deleteById(id);
    }
}
