package br.com.trainer.trainerapi.controller;

import br.com.trainer.trainerapi.model.dto.RequestResultDto;
import br.com.trainer.trainerapi.model.dto.chatbot.ChatbotResultDto;
import br.com.trainer.trainerapi.model.dto.chatbot.ChatbotUpdateInputDto;
import br.com.trainer.trainerapi.service.ChatbotService;
import br.com.trainer.trainerapi.model.dto.chatbot.ChatbotInputDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @GetMapping
    public ResponseEntity<RequestResultDto> getAllChatbots(@PageableDefault Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(chatbotService.listAllChatbots(pageable), false, null));
    }

    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<RequestResultDto> getchatbotByTrainer(@PathVariable Integer trainerId) {
        try {
            ChatbotResultDto result = chatbotService.listChatbotByTrainer(trainerId);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(result, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @GetMapping("{chatbotId}")
    public  ResponseEntity<RequestResultDto> getChabot(@PathVariable Integer chatbotId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(chatbotService.listChatbotbyId(chatbotId),false,null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResultDto(null,true, ex.getMessage()));

        }
    }

    @PostMapping
    public ResponseEntity<RequestResultDto> createChatbot(@RequestBody ChatbotInputDto chatbot) {
        try {
            chatbotService.registerChatbot(chatbot);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<RequestResultDto> updateChatbot(@RequestBody ChatbotUpdateInputDto chatbot, @PathVariable Integer id) {
        try {
            chatbotService.updateChatbot(chatbot, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RequestResultDto(null,false,null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null,true, ex.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RequestResultDto> deleteChatbot(@PathVariable Integer id) {
        try {
            chatbotService.deleteChatbot(id);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null,true, ex.getMessage()));
        }
    }
}