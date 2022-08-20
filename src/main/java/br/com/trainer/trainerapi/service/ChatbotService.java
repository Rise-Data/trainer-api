package br.com.trainer.trainerapi.service;

import br.com.trainer.trainerapi.exception.RowNotFoundException;
import br.com.trainer.trainerapi.model.dto.ChatbotInputDto;
import br.com.trainer.trainerapi.model.dto.ChatbotResultDto;
import br.com.trainer.trainerapi.model.entity.Chatbot;
import br.com.trainer.trainerapi.model.entity.Trainer;
import br.com.trainer.trainerapi.model.repository.ChatbotRepository;
import br.com.trainer.trainerapi.model.repository.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ChatbotService {

    @Autowired
    private ChatbotRepository chatbotRepository;

    @Autowired
    private TrainerRepository trainerRepository;


    public void registerChatbot (ChatbotInputDto chatbot) throws RowNotFoundException {
        if (chatbot == null) {
            throw new NullPointerException("Chatbot can't be null");
        }

        if (chatbot.trainerId() == null) {
            throw new NullPointerException("TrainerId can't be null");
        }

        Trainer chatbotOfTrainer;

        if (trainerRepository.findById(chatbot.trainerId()).isPresent()) {
            chatbotOfTrainer = trainerRepository.findById(chatbot.trainerId()).get();

        } else {
            throw new RowNotFoundException("Trainer not found");
        }
        Chatbot chatbotEntity = new Chatbot(chatbot.name(), chatbotOfTrainer);
        chatbotRepository.save(chatbotEntity);
    }


    public List<Chatbot> listAllChatbots() {
        return chatbotRepository.findAll();
    }

    public List<ChatbotResultDto> listChatbotByTrainer(Integer trainerId) throws RowNotFoundException {
        Trainer trainer;
        if (trainerRepository.findById(trainerId).isPresent()) {
            trainer = trainerRepository.findById(trainerId).get();
        } else {
            throw new RowNotFoundException("Trainer not found");
        }

        List<Chatbot> chatbot = chatbotRepository.findByTrainer(trainer);
        return chatbot
                .stream()
                .map(m -> new ChatbotResultDto(
                        m.getId(),
                        m.getName(),
                        m.getTrainer().getId()
                )).toList();
    }

    public void deleteChatbot(Integer id) throws RowNotFoundException {
        if (chatbotRepository.findById(id).isEmpty()) {
            throw new RowNotFoundException("Chatbot not found");
        }

        chatbotRepository.deleteById(id);
    }
}