package br.com.trainer.trainerapi.model.dto.exercise;

public record ExerciseInputDto(
        String  name,
        Integer repetitions,
        Integer duration,
        String  videoLink,
        String  description,
        Integer training,
        Integer exerciseType) {

}
