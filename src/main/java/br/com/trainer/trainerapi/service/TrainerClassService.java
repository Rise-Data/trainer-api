package br.com.trainer.trainerapi.service;

import br.com.trainer.trainerapi.exception.RowNotFoundException;
import br.com.trainer.trainerapi.model.dto.trainerClass.TrainerClassInputDto;
import br.com.trainer.trainerapi.model.dto.trainerClass.TrainerClassResultDto;
import br.com.trainer.trainerapi.model.dto.trainerClass.TrainerClassUpdateInputDto;
import br.com.trainer.trainerapi.model.entity.TrainerClass;
import br.com.trainer.trainerapi.model.repository.MemberRepository;
import br.com.trainer.trainerapi.model.repository.TrainerClassRepository;
import br.com.trainer.trainerapi.model.repository.TrainerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TrainerClassService {

    private final TrainerClassRepository trainerClassRepository;
    private final TrainerRepository trainerRepository;
    private final MemberRepository memberRepository;

    public TrainerClassService(TrainerClassRepository trainerClassRepository, TrainerRepository trainerRepository, MemberRepository memberRepository) {
        this.trainerClassRepository = trainerClassRepository;
        this.trainerRepository = trainerRepository;
        this.memberRepository = memberRepository;
    }

    public Page<TrainerClassResultDto> listAll(Pageable pageable) {
        return new PageImpl<TrainerClassResultDto>(
                trainerClassRepository.findAll()
                        .stream()
                        .map(t -> new TrainerClassResultDto(
                                t.getId(),
                                t.getDate(),
                                t.getStatus(),
                                t.getTrainer().getId(),
                                t.getMember().getId()
                        )).toList()
        );
    }

    public Page<TrainerClassResultDto> listByMember(Pageable pageable, Integer memberId) throws RowNotFoundException {
        var member = memberRepository.findById(memberId).orElseThrow(() -> new RowNotFoundException("Member not found"));
        return new PageImpl<TrainerClassResultDto>(
                trainerClassRepository.findByMember(member)
                        .stream()
                        .map(t -> new TrainerClassResultDto(
                                t.getId(),
                                t.getDate(),
                                t.getStatus(),
                                t.getTrainer().getId(),
                                t.getMember().getId()
                        )).toList()
        );
    }

    public Page<TrainerClassResultDto> listByTrainer(Pageable pageable, Integer trainerId) throws RowNotFoundException {
        var trainer = trainerRepository.findById(trainerId).orElseThrow(() -> new RowNotFoundException("Trainer not found"));
        return new PageImpl<TrainerClassResultDto>(
                trainerClassRepository.findByTrainer(trainer)
                        .stream()
                        .map(t -> new TrainerClassResultDto(
                                t.getId(),
                                t.getDate(),
                                t.getStatus(),
                                t.getTrainer().getId(),
                                t.getMember().getId()
                        )).toList()
        );
    }

    public void addTrainerClass(TrainerClassInputDto trainerClassInputDto) throws RowNotFoundException {
        if (trainerClassInputDto == null)
            throw new NullPointerException("TrainerClassInput can't be null");
        if (trainerClassInputDto.date() == null)
            throw new NullPointerException("Date can't be null");
        if (trainerClassInputDto.trainerId() == null)
            throw new NullPointerException("TrainerId can't be null");
        if (trainerClassInputDto.memberId() == null)
            throw new NullPointerException("MemberId can't be null");

        var trainer = trainerRepository.findById(trainerClassInputDto.trainerId()).orElseThrow(() -> new RowNotFoundException("Trainer not found"));
        var member = memberRepository.findById(trainerClassInputDto.memberId()).orElseThrow(() -> new RowNotFoundException("Member not found"));
        var trainerClass = new TrainerClass(trainerClassInputDto.date(), false, trainer, member);
        trainerClassRepository.save(trainerClass);
    }

    public void updateTrainerClass(TrainerClassUpdateInputDto trainerClassUpdateInputDto, Integer id) throws RowNotFoundException {
        if (trainerClassUpdateInputDto == null)
            throw new NullPointerException("TrainerClassInput can't be null");
        if (trainerClassUpdateInputDto.date() == null)
            throw new NullPointerException("Date can't be null");

        var trainerClass = trainerClassRepository.findById(id).orElseThrow(() -> new RowNotFoundException("TrainerClass not found"));
        trainerClass.setDate(trainerClassUpdateInputDto.date());
        trainerClassRepository.save(trainerClass);
    }

    public void deleteTrainerClass(Integer id) throws RowNotFoundException {
        var trainerClass = trainerClassRepository.findById(id).orElseThrow(() -> new RowNotFoundException("TrainerClass not found"));
        trainerClassRepository.delete(trainerClass);
    }
}
