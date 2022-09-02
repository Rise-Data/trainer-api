package br.com.trainer.trainerapi.service;


import br.com.trainer.trainerapi.model.dto.ChatbotInputDto;
import br.com.trainer.trainerapi.model.entity.Chatbot;
import br.com.trainer.trainerapi.model.repository.ChatbotRepository;

import br.com.trainer.trainerapi.model.repository.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ChatbotService {

    @Autowired
    private ChatbotRepository chatbotRepository;
    private TrainerRepository trainerRepository;

    public void registerChatbot (ChatbotInputDto chatbot){
        trainerRepository.save();
    }


    public List<Chatbot> listAllChatbots() {
        return chatbotRepository.findAll();
    }

    public List<Chatbot> listChatbotByTrainer(Integer trainerId) {
        return  chatbotRepository.findByTrainer(trainerId);
    }

    public void deleteChatbot(Integer id) {
        chatbotRepository.deleteById(id);
    }
}