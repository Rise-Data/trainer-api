package br.com.trainer.trainerapi.model.dto.member;

public record MemberResultDto(
        Integer id,
        String name,
        String phone,
        Boolean active,
        Integer trainingSequence,
        Integer trainerId
) {
}
