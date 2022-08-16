package br.com.trainer.trainerapi.service;

import br.com.trainer.trainerapi.exception.RowNotFoundException;
import br.com.trainer.trainerapi.model.dto.MemberInputDto;
import br.com.trainer.trainerapi.model.dto.MemberResultDto;
import br.com.trainer.trainerapi.model.entity.Member;
import br.com.trainer.trainerapi.model.entity.Trainer;
import br.com.trainer.trainerapi.model.repository.MemberRepository;
import br.com.trainer.trainerapi.model.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TrainerRepository trainerRepository;

    public List<MemberResultDto> listAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members
                .stream()
                .map(m -> new MemberResultDto(
                        m.getId(),
                        m.getName(),
                        m.getPhone(),
                        m.getActive(),
                        m.getTrainingSequence(),
                        m.getTrainer().getId(),
                        m.getTrainings())).toList();
    }

    public List<MemberResultDto> listMembersByTrainer(Integer trainerId) throws RowNotFoundException {
        Trainer trainer;
        if (trainerRepository.findById(trainerId).isPresent()) {
            trainer = trainerRepository.findById(trainerId).get();
        } else {
            throw new RowNotFoundException("Trainer not found");
        }

        List<Member> members = memberRepository.findByTrainer(trainer);
        return members
                .stream()
                .map(m -> new MemberResultDto(
                        m.getId(),
                        m.getName(),
                        m.getPhone(),
                        m.getActive(),
                        m.getTrainingSequence(),
                        m.getTrainer().getId(),
                        m.getTrainings())).toList();
    }

    public void addMember(MemberInputDto member) throws RowNotFoundException {
        if (member == null) {
            throw new NullPointerException("Member can't be null");
        }

        if (member.trainerId() == null) {
            throw new NullPointerException("TrainerId can't be null");
        }

        Trainer trainerOfMember;

        if (trainerRepository.findById(member.trainerId()).isPresent()) {
            trainerOfMember = trainerRepository.findById(member.trainerId()).get();

        } else {
            throw new RowNotFoundException("Trainer not found");
        }
        Member memberEntity = new Member(member.name(), member.phone(), trainerOfMember);
        memberRepository.save(memberEntity);
    }

    public void updateMember(MemberInputDto member, Integer id) throws RowNotFoundException {
        if (member == null) {
            throw new NullPointerException("Member can't be null");
        }
        if (memberRepository.findById(id).isPresent()) {
            Member memberEntity = memberRepository.findById(id).get();
            memberEntity.setName(member.name());
            memberEntity.setPhone(member.phone());
            memberEntity.setTrainings(member.trainings());
            memberRepository.save(memberEntity);
        } else {
            throw new RowNotFoundException("Member not found");
        }
    }

    public void deleteMember(Integer id) throws RowNotFoundException {
        if (memberRepository.findById(id).isPresent()) {
            memberRepository.deleteById(id);
        } else {
            throw new RowNotFoundException("Member not found");
        }
    }
}
