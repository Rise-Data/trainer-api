package br.com.trainer.trainerapi.service;


import br.com.trainer.trainerapi.exception.RowNotFoundException;
import br.com.trainer.trainerapi.model.dto.ChatbotInputDto;
import br.com.trainer.trainerapi.model.entity.Chatbot;
import br.com.trainer.trainerapi.model.repository.ChatbotRepository;

import br.com.trainer.trainerapi.model.repository.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class ChatbotService {

    @Autowired
    private ChatbotRepository chatbotRepository;
    @Autowired
    private TrainerRepository trainerRepository;

    public void registerChatbot (ChatbotInputDto chatbot) throws RowNotFoundException {
        if (chatbot == null){
            throw new NullPointerException("Chatbot Can't be null");
        }
        if (chatbot.name() == null || chatbot.name() == "") {
            throw new NullPointerException("Name Can't be null or empty");
        }
        var chatbotEntity  = new Chatbot (chatbot.name());

        chatbotRepository.save(chatbotEntity);
    }

    public List<Chatbot> listAllChatbots() {
        return chatbotRepository.findAll();
    }

    public List<Chatbot> listChatbotByTrainer(Integer trainerId) throws RowNotFoundException {

        var trainer = trainerRepository.findById(trainerId).orElseThrow(() -> new RowNotFoundException("Trainer Not Found"));
        return  chatbotRepository.findByTrainer(trainer.getId());
    }

    public Optional<Chatbot> listChatbotbyId(Integer id) throws  RowNotFoundException {
        var chatbotid = chatbotRepository.findById(id).orElseThrow(() -> new RuntimeException("Chatbot Not found"));
        return  chatbotRepository.findById(chatbotid.getId());
    }

    public void updateChatbot (ChatbotInputDto chatbot, Integer id) throws RowNotFoundException {
        var chatbotEntity = chatbotRepository.findById(id).orElseThrow(()-> new RowNotFoundException("Chatbot Not Found!"));
        if (chatbot == null){
            throw new NullPointerException("Chatbot Can't be null");
        }
        if (chatbot.name() == null || chatbot.name() == "") {
            throw new  NullPointerException("Name Can't be null or empty");
        }
        chatbotEntity.setName(chatbot.name());
        chatbotRepository.save(chatbotEntity);
    }

    public void deleteChatbot(Integer id) throws RowNotFoundException{
        var chatbot =  chatbotRepository.findById(id).orElseThrow(()-> new RowNotFoundException("Chatbot Not Found"));
        chatbotRepository.delete(chatbot);
    }
}