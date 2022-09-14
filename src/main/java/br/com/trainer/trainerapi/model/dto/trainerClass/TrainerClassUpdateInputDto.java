package br.com.trainer.trainerapi.model.dto.trainerClass;

import java.time.LocalDateTime;

public record TrainerClassUpdateInputDto(
        LocalDateTime date
) {
}
