package br.com.trainer.trainerapi.model.repository;

import br.com.trainer.trainerapi.model.entity.Chatbot;
import br.com.trainer.trainerapi.model.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatbotRepository extends JpaRepository<Chatbot, Integer> {
    List<Chatbot> findByTrainer(Trainer trainer);
}
