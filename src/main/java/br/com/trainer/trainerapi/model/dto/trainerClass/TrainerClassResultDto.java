package br.com.trainer.trainerapi.model.dto.trainerClass;

import java.time.LocalDateTime;

public record TrainerClassResultDto(
        Integer id,
        LocalDateTime date,
        Boolean status,
        Integer trainerId,
        Integer memberId
) {
}
