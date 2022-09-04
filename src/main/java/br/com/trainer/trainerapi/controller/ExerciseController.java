package br.com.trainer.trainerapi.controller;

import br.com.trainer.trainerapi.model.dto.*;
import br.com.trainer.trainerapi.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class ExerciseController {
    @Autowired
    ExerciseService exerciseService;

    @GetMapping("/api/exercise")
    public ResponseEntity<RequestResultDto> getAllExercises() {
        try {
            List<ExerciseResultDto> resultDto = exerciseService.listAllExercises();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new RequestResultDto(resultDto, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }


    @PostMapping("/api/exercise")
    public ResponseEntity<RequestResultDto> createExercise(@RequestBody ExerciseInputDto exercise) {
        try {
            exerciseService.addExercise(exercise);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @PutMapping("/api/exercise/{id}")
    public ResponseEntity<RequestResultDto> updateExercise(@RequestBody ExerciseInputDto exercise, @PathVariable Integer id) {
        try {
            exerciseService.updateExercise(exercise, id);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @DeleteMapping("/api/exercise/{id}")
    public ResponseEntity<RequestResultDto> removeExercise(@PathVariable Integer id) {
        try {
            exerciseService.deleteExercise(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }
}
