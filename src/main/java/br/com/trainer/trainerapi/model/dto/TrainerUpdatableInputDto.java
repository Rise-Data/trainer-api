package br.com.trainer.trainerapi.model.dto;

public record TrainerUpdatableInputDto(
        String user,
        String email,
        String phone
) {
}
