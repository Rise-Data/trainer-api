package br.com.trainer.trainerapi.controller;

import br.com.trainer.trainerapi.model.dto.RequestResultDto;
import br.com.trainer.trainerapi.model.entity.Chatbot;
import br.com.trainer.trainerapi.service.ChatbotService;
import br.com.trainer.trainerapi.model.dto.ChatbotInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatbotController {

    @Autowired
    ChatbotService chatbotService;


    @PostMapping("/api/chatbot")
    public ResponseEntity<RequestResultDto> createChatbot(@RequestBody ChatbotInputDto chatbot) {
        try {
            chatbotService.registerChatbot(chatbot);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }


    @GetMapping("/api/chatbot")
    public ResponseEntity<RequestResultDto> getAllChatbots(){
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(chatbotService.listAllChatbots(), false, null));
    }



    @GetMapping("/api/chatbot/{trainerId}")
    public ResponseEntity<RequestResultDto> getchatbotByTrainer(@PathVariable Integer trainerId) {
        try {
            List<Chatbot> resultDtos = chatbotService.listChatbotByTrainer(trainerId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new RequestResultDto(resultDtos, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @GetMapping("/api/chatbot/{chatbotId}")
    public  ResponseEntity<RequestResultDto> getChabot(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new RequestResultDto(chatbotService.listChatbotbyId(id),false,null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new RequestResultDto(null,true, ex.getMessage()));

        }
    }



    @GetMapping("/api/chatbot/{id}")
    public ResponseEntity<RequestResultDto> updateChatbot(@RequestBody ChatbotInputDto chatbot, @PathVariable Integer id) {
        try {
            chatbotService.updateChatbot(chatbot, id);
            return  ResponseEntity.status(HttpStatus.CREATED)
                    .body(new RequestResultDto(null,false,null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RequestResultDto(null,true, ex.getMessage()));
        }

    }


    @DeleteMapping("/api/chatbot/{id}")
    public ResponseEntity<RequestResultDto> deleteChatbot(@PathVariable Integer id) {
        try {
            chatbotService.deleteChatbot(id);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null,true, ex.getMessage()));
        }
    }
}