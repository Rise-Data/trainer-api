package br.com.trainer.trainerapi.model.repository;

import br.com.trainer.trainerapi.model.entity.Member;
import br.com.trainer.trainerapi.model.entity.Trainer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findByTrainer(Trainer trainer, Pageable pageable);

}
