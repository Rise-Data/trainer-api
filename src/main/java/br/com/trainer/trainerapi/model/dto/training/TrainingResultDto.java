package br.com.trainer.trainerapi.model.dto.training;

import java.time.LocalDate;
import java.util.List;

public record TrainingResultDto(
        Integer id,
        LocalDate trainingDay,
        Integer member,
        List<Integer> exercises
) {
}
