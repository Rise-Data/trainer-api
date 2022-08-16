package br.com.trainer.trainerapi.model.dto;

import br.com.trainer.trainerapi.model.entity.Training;

import java.util.List;

public record MemberInputDto(
      String name,
      String phone,
      Integer trainerId,
      List<Training> trainings
) {
}
