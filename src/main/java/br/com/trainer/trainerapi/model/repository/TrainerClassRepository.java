package br.com.trainer.trainerapi.model.repository;

import br.com.trainer.trainerapi.model.entity.Member;
import br.com.trainer.trainerapi.model.entity.Trainer;
import br.com.trainer.trainerapi.model.entity.TrainerClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerClassRepository extends JpaRepository<TrainerClass, Integer> {
    List<TrainerClass> findByMember(Member member);
    List<TrainerClass> findByTrainer(Trainer trainer);
}
