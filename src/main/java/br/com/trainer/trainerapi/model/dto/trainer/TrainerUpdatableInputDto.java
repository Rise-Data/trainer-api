package br.com.trainer.trainerapi.model.dto.trainer;

public record TrainerUpdatableInputDto(
        String user,
        String email,
        String phone
) {
}
