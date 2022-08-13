package br.com.trainer.trainerapi.model.dto;

public record RequestResultDto(
        Object result,
        Boolean hasError,
        String msg) {
}
