package br.com.trainer.trainerapi.repository;

import br.com.trainer.trainerapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
