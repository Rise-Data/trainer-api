package br.com.trainer.trainerapi.model.dto.exercise;

public record ExerciseResultDto(
        Integer id,
        String  name,
        Integer repetitions,
        Integer idTraining,
        Integer exerciseType,
        String description,
        String  linkVideo

) {

}
