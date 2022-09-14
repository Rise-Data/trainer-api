package br.com.trainer.trainerapi.model.dto.trainerClass;

import java.time.LocalDateTime;

public record TrainerClassInputDto(
        LocalDateTime date,
        Integer trainerId,
        Integer memberId
) {
}
