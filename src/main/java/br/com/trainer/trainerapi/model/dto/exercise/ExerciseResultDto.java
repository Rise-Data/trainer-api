package br.com.trainer.trainerapi.model.dto.exercise;

public record ExerciseResultDto(
        Integer id,
        String  name,
        Integer repetitions,
        Integer idTraining,
        Integer idTrainingType,
        String description,
        String  linkVideo

) {

}
