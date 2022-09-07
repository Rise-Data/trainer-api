package br.com.trainer.trainerapi.model.dto.trainer;

public record TrainerInputDto(
        String user,
        String password,
        String email,
        String cpf,
        String phone) {
}
