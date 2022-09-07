package br.com.trainer.trainerapi.model.dto.exercise;

public record ExerciseInputDto(
        String  name,
        Integer repetitions,
        String  videoLink,
        String  description,
        Integer training,
        Integer trainingTypeId) {

}
