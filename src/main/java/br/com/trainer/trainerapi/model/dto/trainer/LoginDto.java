package br.com.trainer.trainerapi.model.dto.trainer;

public record LoginDto(
        String email,
        String password
) {
}
