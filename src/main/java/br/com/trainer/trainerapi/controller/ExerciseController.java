package br.com.trainer.trainerapi.controller;

import br.com.trainer.trainerapi.model.dto.*;
import br.com.trainer.trainerapi.model.dto.exercise.ExerciseInputDto;
import br.com.trainer.trainerapi.model.dto.exercise.ExerciseResultDto;
import br.com.trainer.trainerapi.model.dto.exercise.ExerciseUpdateInputDto;
import br.com.trainer.trainerapi.service.ExerciseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/exercise")
public class ExerciseController {
    ExerciseService exerciseService;
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public ResponseEntity<RequestResultDto> getAllExercises(@PageableDefault Pageable pageable) {
        try {
            Page<ExerciseResultDto> resultDto = exerciseService.listAllExercises(pageable);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(resultDto, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @GetMapping("/exerciseType/{exerciseTypeId}")
    public ResponseEntity<RequestResultDto> getByExerciseType(@PageableDefault Pageable pageable, @PathVariable Integer exerciseTypeId) {
        try {
            Page<ExerciseResultDto> resultDto = exerciseService.listByExerciseType(pageable, exerciseTypeId);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(resultDto, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @GetMapping("/training/{trainingId}")
    public ResponseEntity<RequestResultDto> getByTraining(@PageableDefault Pageable pageable, @PathVariable Integer trainingId) {
        try {
            Page<ExerciseResultDto> resultDto = exerciseService.listByTraining(pageable, trainingId);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(resultDto, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<RequestResultDto> createExercise(@RequestBody ExerciseInputDto exercise) {
        try {
            exerciseService.addExercise(exercise);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<RequestResultDto> updateExercise(@RequestBody ExerciseUpdateInputDto exercise, @PathVariable Integer id) {
        try {
            exerciseService.updateExercise(exercise, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RequestResultDto> removeExercise(@PathVariable Integer id) {
        try {
            exerciseService.deleteExercise(id);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }
}
