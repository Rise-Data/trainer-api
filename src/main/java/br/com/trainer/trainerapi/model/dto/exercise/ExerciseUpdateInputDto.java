package br.com.trainer.trainerapi.model.dto.exercise;

public record ExerciseUpdateInputDto(
        String name,
        Integer repetitions,
        String videoLink,
        String description,
        Integer trainingType
) {
}
