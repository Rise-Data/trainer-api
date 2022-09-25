package br.com.trainer.trainerapi.service;

import br.com.trainer.trainerapi.exception.NotAuthorizedException;
import br.com.trainer.trainerapi.exception.RowNotFoundException;
import br.com.trainer.trainerapi.model.dto.trainer.TrainerInputDto;
import br.com.trainer.trainerapi.model.dto.trainer.TrainerResultDto;
import br.com.trainer.trainerapi.model.dto.trainer.TrainerUpdatableInputDto;
import br.com.trainer.trainerapi.model.entity.Member;
import br.com.trainer.trainerapi.model.entity.Trainer;
import br.com.trainer.trainerapi.model.repository.TrainerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public TrainerResultDto login(String email, String password) throws RowNotFoundException, NotAuthorizedException {
        var trainer = trainerRepository.findByEmail(email).orElseThrow(() -> new RowNotFoundException("Trainer not found"));
        if (!trainer.getPassword().equals(password))
            throw new NotAuthorizedException("Password incorrect");
        return new TrainerResultDto(
                trainer.getId(),
                trainer.getUser(),
                trainer.getEmail(),
                trainer.getPhone(),
                trainer.getMembers()
                        .stream()
                        .map(Member::getId)
                        .toList());
    }

    public void registerTrainer(TrainerInputDto trainer) {
        validateTrainerInput(trainer);
        var trainerEntity = new Trainer(trainer.user(), trainer.password(), trainer.email(), trainer.cpf(), trainer.phone());
        trainerRepository.save(trainerEntity);
    }

    public Page<TrainerResultDto> listAllTrainers(Pageable pageable) {
        return new PageImpl<TrainerResultDto>(trainerRepository.findAll(pageable)
                .stream()
                .map(t -> new TrainerResultDto(
                        t.getId(),
                        t.getUser(),
                        t.getEmail(),
                        t.getPhone(),
                        t.getMembers().stream().map(Member::getId).toList()
                )).toList());
    }

    public TrainerResultDto updateTrainer(TrainerUpdatableInputDto trainer, Integer id) throws RowNotFoundException {
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
        trainerEntity.setPassword(trainer.password());
        trainerRepository.save(trainerEntity);

        return new TrainerResultDto(
                trainerEntity.getId(),
                trainerEntity.getUser(),
                trainerEntity.getEmail(),
                trainerEntity.getPhone(),
                trainerEntity.getMembers()
                        .stream()
                        .map(Member::getId).toList());
    }

    public TrainerResultDto listTrainer(Integer id) throws RowNotFoundException {
        if (trainerRepository.findById(id).isPresent()) {
            Trainer trainerEntity = trainerRepository.findById(id).get();
            return new TrainerResultDto(
                    trainerEntity.getId(),
                    trainerEntity.getUser(),
                    trainerEntity.getEmail(),
                    trainerEntity.getPhone(),
                    trainerEntity.getMembers().stream().map(Member::getId).toList()
            );
        }

        throw new RowNotFoundException("Trainer not found");
    }

    public void deleteTrainer(Integer id) throws RowNotFoundException {
        var trainer = trainerRepository.findById(id).orElseThrow(() -> new RowNotFoundException("Trainer not found"));
        trainerRepository.delete(trainer);
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

        if (trainer.password().length() > 8)
            throw new IllegalArgumentException("The maximum number of characters in the password is 8");
    }
}
