package br.com.trainer.trainerapi.model.repository;

import br.com.trainer.trainerapi.model.entity.Chatbot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatbotRepository extends JpaRepository<Chatbot, Integer> {
}
