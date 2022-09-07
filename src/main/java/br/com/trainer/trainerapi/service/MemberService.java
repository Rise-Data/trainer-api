package br.com.trainer.trainerapi.service;

import br.com.trainer.trainerapi.exception.RowNotFoundException;
import br.com.trainer.trainerapi.model.dto.member.MemberInputDto;
import br.com.trainer.trainerapi.model.dto.member.MemberResultDto;
import br.com.trainer.trainerapi.model.entity.Member;
import br.com.trainer.trainerapi.model.repository.MemberRepository;
import br.com.trainer.trainerapi.model.repository.TrainerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final TrainerRepository trainerRepository;

    public MemberService(MemberRepository memberRepository, TrainerRepository trainerRepository) {
        this.memberRepository = memberRepository;
        this.trainerRepository = trainerRepository;
    }

    public Page<MemberResultDto> listAllMembers(Pageable pageable) {
        return  new PageImpl<MemberResultDto>(memberRepository.findAll(pageable)
                .stream()
                .map(m -> new MemberResultDto(
                        m.getId(),
                        m.getName(),
                        m.getPhone(),
                        m.getActive(),
                        m.getTrainingSequence(),
                        m.getTrainer().getId(),
                        m.getTrainings())).toList());
    }

    public Page<MemberResultDto> listMembersByTrainer(Pageable pageable, Integer trainerId) throws RowNotFoundException {
        var trainer = trainerRepository.findById(trainerId).orElseThrow(() -> new RowNotFoundException("Trainer not found"));
        return new PageImpl<MemberResultDto>(memberRepository.findByTrainer(trainer, pageable)
                .stream()
                .map(m -> new MemberResultDto(
                        m.getId(),
                        m.getName(),
                        m.getPhone(),
                        m.getActive(),
                        m.getTrainingSequence(),
                        m.getTrainer().getId(),
                        m.getTrainings())).toList());
    }

    public void addMember(MemberInputDto memberInput) throws RowNotFoundException {
        if (memberInput == null)
            throw new NullPointerException("Member can't be null");

        if (memberInput.trainerId() == null)
            throw new NullPointerException("TrainerId can't be null");

        var trainer = trainerRepository.findById(memberInput.trainerId()).orElseThrow(() -> new RowNotFoundException("Trainer not found"));
        Member member = new Member(memberInput.name(), memberInput.phone(), trainer);
        memberRepository.save(member);
    }

    public void updateMember(MemberInputDto memberInput, Integer id) throws RowNotFoundException {
        if (memberInput == null)
            throw new NullPointerException("Member can't be null");

        var member = memberRepository.findById(id).orElseThrow(() -> new RowNotFoundException("Member not found"));
        member.setName(memberInput.name());
        member.setPhone(memberInput.phone());
        member.setTrainings(memberInput.trainings());
        memberRepository.save(member);
    }

    public void deleteMember(Integer id) throws RowNotFoundException {
        var member = memberRepository.findById(id).orElseThrow(() -> new RowNotFoundException("Member not found"));
        memberRepository.delete(member);
    }
}
