package br.com.trainer.trainerapi.model.dto;

public record TrainerInputDto(
        String user,
        String password,
        String email,
        String cpf,
        String phone) {
}
