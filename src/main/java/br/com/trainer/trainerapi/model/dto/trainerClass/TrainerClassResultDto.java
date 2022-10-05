package br.com.trainer.trainerapi.model.dto.trainerClass;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record TrainerClassResultDto(
        Integer id,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime date,
        Boolean status,
        Integer trainerId,
        Integer memberId
) {
}
