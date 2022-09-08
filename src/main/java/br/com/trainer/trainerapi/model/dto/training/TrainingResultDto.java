package br.com.trainer.trainerapi.model.dto.training;

import java.time.LocalDate;

public record TrainingResultDto(
        Integer id,
        LocalDate trainingDay,
        Integer member
) {
}
