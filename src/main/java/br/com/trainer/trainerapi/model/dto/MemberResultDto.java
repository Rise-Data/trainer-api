package br.com.trainer.trainerapi.model.dto;

import br.com.trainer.trainerapi.model.entity.Training;

import java.util.List;

public record MemberResultDto(
        Integer id,
        String name,
        String phone,
        Boolean active,
        Integer trainingSequence,
        Integer trainerId,
        List<Training> trainings
) {
}
