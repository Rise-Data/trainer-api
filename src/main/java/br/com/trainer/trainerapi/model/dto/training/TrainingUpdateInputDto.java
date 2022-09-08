package br.com.trainer.trainerapi.model.dto.training;

import java.time.LocalDate;

public record TrainingUpdateInputDto(
        LocalDate trainingDay
) {
}
