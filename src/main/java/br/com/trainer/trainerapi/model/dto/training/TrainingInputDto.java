package br.com.trainer.trainerapi.model.dto.training;

import java.time.LocalDate;

public record TrainingInputDto(
        LocalDate trainingDay,
        Boolean trainingStatus,
        Integer member
) {
}
