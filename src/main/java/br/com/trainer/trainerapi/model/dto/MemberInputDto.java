package br.com.trainer.trainerapi.model.dto;

public record MemberInputDto(
      String name,
      String phone,
      Integer trainerId
) {
}
