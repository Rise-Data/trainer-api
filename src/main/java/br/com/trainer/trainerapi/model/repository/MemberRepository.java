package br.com.trainer.trainerapi.model.repository;

import br.com.trainer.trainerapi.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
