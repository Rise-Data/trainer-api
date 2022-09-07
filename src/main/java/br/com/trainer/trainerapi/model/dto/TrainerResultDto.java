package br.com.trainer.trainerapi.model.dto;

import br.com.trainer.trainerapi.model.entity.Chatbot;
import br.com.trainer.trainerapi.model.entity.Member;

import java.util.List;

public record TrainerResultDto(
        Integer id,
        String user,
        String email,
        String phone,
        List<Integer> membersIds,
        Integer chatbotId) {
}
