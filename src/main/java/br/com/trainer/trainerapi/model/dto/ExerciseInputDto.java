package br.com.trainer.trainerapi.model.dto;

public record ExerciseInputDto(
        String  name,
        Integer repetitions,
        String  videoLink,
        String  description,
        Integer trainingTypeId) {

}
