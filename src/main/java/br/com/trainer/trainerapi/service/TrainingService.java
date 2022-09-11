package br.com.trainer.trainerapi.service;

import br.com.trainer.trainerapi.exception.RowNotFoundException;
import br.com.trainer.trainerapi.model.dto.training.TrainingInputDto;
import br.com.trainer.trainerapi.model.dto.training.TrainingResultDto;
import br.com.trainer.trainerapi.model.dto.training.TrainingUpdateInputDto;
import br.com.trainer.trainerapi.model.entity.Training;
import br.com.trainer.trainerapi.model.repository.MemberRepository;
import br.com.trainer.trainerapi.model.repository.TrainingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;
    private final MemberRepository memberRepository;

    public TrainingService(TrainingRepository trainingRepository, MemberRepository memberRepository) {
        this.trainingRepository = trainingRepository;
        this.memberRepository = memberRepository;
    }

    public Page<TrainingResultDto> listAllTrainings(Pageable pageable) {
        return new PageImpl<TrainingResultDto>(
                trainingRepository.findAll()
                        .stream()
                        .map(t -> new TrainingResultDto(
                                t.getId(),
                                t.getTrainingDay(),
                                t.getMember().getId()
                        ))
                        .toList()
        );
    }

    public TrainingResultDto listTraining(Integer id) throws RowNotFoundException {
        var training = trainingRepository.findById(id).orElseThrow(() -> new RowNotFoundException("Training not found"));
        return new TrainingResultDto(training.getId(), training.getTrainingDay(), training.getMember().getId());
    }

    public Page<TrainingResultDto> listTrainingsByMember(Pageable pageable, Integer memberId) throws RowNotFoundException {
        var member = memberRepository.findById(memberId).orElseThrow(() -> new RowNotFoundException("Member not found"));
        return new PageImpl<TrainingResultDto>(
                trainingRepository.findByMember(pageable, member)
                        .stream()
                        .map(t -> new TrainingResultDto(
                                t.getId(),
                                t.getTrainingDay(),
                                t.getMember().getId()
                        ))
                        .toList()
        );
    }

    public void addTraining(TrainingInputDto trainingInput) throws RowNotFoundException {
        if (trainingInput == null)
            throw new NullPointerException("Training can't be null");

        if (trainingInput.trainingDay() == null)
            throw new NullPointerException("Training day can't be null");

        if (trainingInput.member() == null)
            throw new NullPointerException("Member can't be null");

        if (trainingInput.trainingStatus() == null)
            throw new NullPointerException("TrainingStatus can't be null");

        var member = memberRepository.findById(trainingInput.member()).orElseThrow(() -> new RowNotFoundException("Member not found"));
        var training = new Training(trainingInput.trainingDay(), trainingInput.trainingStatus(), member);
        trainingRepository.save(training);
    }

    public void updateTraining(TrainingUpdateInputDto trainingInput, Integer id) throws RowNotFoundException {
        if (trainingInput == null)
            throw new NullPointerException("Training can't be null");

        if (trainingInput.trainingDay() == null)
            throw new NullPointerException("Training day can't be null");

        if (trainingInput.trainingStatus() == null)
            throw new NullPointerException("TrainingStatus can't be null");

        var training = trainingRepository.findById(id).orElseThrow(() -> new RowNotFoundException("Training not found"));
        training.setTrainingDay(trainingInput.trainingDay());
        training.setTrainingStatus(trainingInput.trainingStatus());
        trainingRepository.save(training);
    }

    public void deleteTraining(Integer id) throws RowNotFoundException {
        var training = trainingRepository.findById(id).orElseThrow(() -> new RowNotFoundException("Training not found"));
        trainingRepository.delete(training);
    }
}
