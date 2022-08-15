package br.com.trainer.trainerapi.model.dto;

import br.com.trainer.trainerapi.model.entity.Training;

import java.util.List;

public record MemberResultDto(
        String name,
        String phone,
        Boolean active,
        Integer trainingSequence,
        TrainerResultDto trainer,
        List<Training> trainings
) {
}
