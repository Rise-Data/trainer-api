package br.com.trainer.trainerapi.model.dto.training;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public record TrainingResultDto(
        Integer id,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate trainingDay,
        Integer member,
        List<Integer> exercises
) {
}
