package br.com.trainer.trainerapi.model.repository;

import br.com.trainer.trainerapi.model.entity.Member;
import br.com.trainer.trainerapi.model.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findByTrainer(Trainer trainer);

}
