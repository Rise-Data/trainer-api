package br.com.trainer.trainerapi.service;

import br.com.trainer.trainerapi.exception.RowNotFoundException;
import br.com.trainer.trainerapi.model.dto.chatbot.ChatbotInputDto;
import br.com.trainer.trainerapi.model.dto.chatbot.ChatbotResultDto;
import br.com.trainer.trainerapi.model.dto.chatbot.ChatbotUpdateInputDto;
import br.com.trainer.trainerapi.model.entity.Chatbot;
import br.com.trainer.trainerapi.model.repository.ChatbotRepository;
import br.com.trainer.trainerapi.model.repository.TrainerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ChatbotService {

    private final ChatbotRepository chatbotRepository;
    private final TrainerRepository trainerRepository;

    public ChatbotService(ChatbotRepository chatbotRepository, TrainerRepository trainerRepository) {
        this.chatbotRepository = chatbotRepository;
        this.trainerRepository = trainerRepository;
    }

    public void registerChatbot (ChatbotInputDto chatbotInput) throws RowNotFoundException {
        if (chatbotInput == null)
            throw new NullPointerException("Chatbot Can't be null");

        if (chatbotInput.name() == null || chatbotInput.name().equals(""))
            throw new NullPointerException("Name Can't be null or empty");

        var trainer = trainerRepository.findById(chatbotInput.trainer()).orElseThrow(() -> new RowNotFoundException("Trainer not found"));
        var chatbot  = new Chatbot(chatbotInput.name(), trainer);
        chatbotRepository.save(chatbot);
    }

    public Page<ChatbotResultDto> listAllChatbots(Pageable pageable) {
        return new PageImpl<ChatbotResultDto>(chatbotRepository.findAll(pageable)
                .stream()
                .map(c -> new ChatbotResultDto(
                        c.getId(),
                        c.getName(),
                        c.getTrainer().getId())).toList());
    }

    public ChatbotResultDto listChatbotByTrainer(Integer trainerId) throws RowNotFoundException {
        var trainer = trainerRepository.findById(trainerId).orElseThrow(() -> new RowNotFoundException("Trainer Not Found"));
        var chatbot = chatbotRepository.findByTrainer(trainer);
        return new ChatbotResultDto(chatbot.getId(), chatbot.getName(), chatbot.getTrainer().getId());
    }

    public ChatbotResultDto listChatbotbyId(Integer id) throws  RowNotFoundException {
        var chatbot = chatbotRepository.findById(id).orElseThrow(() -> new RuntimeException("Chatbot Not found"));
        return new ChatbotResultDto(chatbot.getId(), chatbot.getName(), chatbot.getTrainer().getId());
    }

    public void updateChatbot (ChatbotUpdateInputDto chatbotInput, Integer id) throws RowNotFoundException {
        var chatbot = chatbotRepository.findById(id).orElseThrow(()-> new RowNotFoundException("Chatbot Not Found!"));
        if (chatbotInput == null)
            throw new NullPointerException("Chatbot Can't be null");

        if (chatbotInput.name() == null || chatbotInput.name().equals(""))
            throw new  NullPointerException("Name Can't be null or empty");

        chatbot.setName(chatbotInput.name());
        chatbotRepository.save(chatbot);
    }

    public void deleteChatbot(Integer id) throws RowNotFoundException{
        var chatbot =  chatbotRepository.findById(id).orElseThrow(()-> new RowNotFoundException("Chatbot Not Found"));
        chatbotRepository.delete(chatbot);
    }
}