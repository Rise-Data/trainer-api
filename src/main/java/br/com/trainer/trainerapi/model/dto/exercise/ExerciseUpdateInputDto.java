package br.com.trainer.trainerapi.model.dto.exercise;

public record ExerciseUpdateInputDto(
        String name,
        Integer repetitions,
        Integer duration,
        String videoLink,
        String description,
        Integer trainingType
) {
}
