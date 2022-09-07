package br.com.trainer.trainerapi.model.repository;

import br.com.trainer.trainerapi.model.entity.Chatbot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ChatbotRepository extends JpaRepository<Chatbot, Integer> {

    List<Chatbot> findByTrainer(Integer trainer);
}
