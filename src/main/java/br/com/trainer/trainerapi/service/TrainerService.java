package br.com.trainer.trainerapi.service;

import br.com.trainer.trainerapi.exception.RowNotFoundException;
import br.com.trainer.trainerapi.model.dto.TrainerInputDto;
import br.com.trainer.trainerapi.model.dto.TrainerResultDto;
import br.com.trainer.trainerapi.model.dto.TrainerUpdatableInputDto;
import br.com.trainer.trainerapi.model.entity.Member;
import br.com.trainer.trainerapi.model.entity.Trainer;
import br.com.trainer.trainerapi.model.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    public void registerTrainer(TrainerInputDto trainer) {
        validateTrainerInput(trainer);
        var trainerEntity = new Trainer(trainer.user(), trainer.password(), trainer.email(), trainer.cpf(), trainer.phone());
        trainerRepository.save(trainerEntity);
    }

    public List<TrainerResultDto> listAllTrainers() {
        return trainerRepository.findAll()
                .stream()
                .map(t -> new TrainerResultDto(
                        t.getId(),
                        t.getUser(),
                        t.getEmail(),
                        t.getPhone(),
                        t.getMembers().stream().map(Member::getId).toList(),
                        t.getChatbot().getId()
                )).toList();
    }

    public void updateTrainer(TrainerUpdatableInputDto trainer, Integer id) throws RowNotFoundException {
        if (trainer == null)
            throw new NullPointerException("Trainer can't be null");

        if (trainer.user() == null)
            throw new NullPointerException("User can't be null");

        if (trainer.email() == null)
            throw new NullPointerException("Email can't be null");

        var trainerEntity = trainerRepository.findById(id).orElseThrow(() -> new RowNotFoundException("Trainer not found"));
        trainerEntity.setUser(trainer.user());
        trainerEntity.setEmail(trainer.email());
        trainerEntity.setPhone(trainer.phone());
        trainerRepository.save(trainerEntity);
    }

    public TrainerResultDto listTrainer(Integer id) throws RowNotFoundException {
        if (trainerRepository.findById(id).isPresent()) {
            Trainer trainerEntity = trainerRepository.findById(id).get();
            return new TrainerResultDto(
                    trainerEntity.getId(),
                    trainerEntity.getUser(),
                    trainerEntity.getEmail(),
                    trainerEntity.getPhone(),
                    trainerEntity.getMembers().stream().map(Member::getId).toList(),
                    trainerEntity.getChatbot().getId()
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

    private void validateTrainerInput(TrainerInputDto trainer) {
        if (trainer == null)
            throw new NullPointerException("Trainer can't be null");

        if (trainer.user() == null)
            throw new NullPointerException("User can't be null");

        if (trainer.password() == null)
            throw new NullPointerException("Password can't be null");

        if (trainer.email() == null)
            throw new NullPointerException("Email can't be null");

        if (trainer.cpf() == null)
            throw new NullPointerException("CPF can't be null");
    }
}
