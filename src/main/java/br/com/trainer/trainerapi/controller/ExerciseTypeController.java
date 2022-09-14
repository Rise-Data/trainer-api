package br.com.trainer.trainerapi.controller;

import br.com.trainer.trainerapi.model.dto.RequestResultDto;
import br.com.trainer.trainerapi.model.dto.exerciseType.ExerciseTypeInput;
import br.com.trainer.trainerapi.service.ExerciseTypeService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exerciseType")
public class ExerciseTypeController {
    private final ExerciseTypeService exerciseTypeService;

    public ExerciseTypeController(ExerciseTypeService exerciseTypeService) {
        this.exerciseTypeService = exerciseTypeService;
    }

    @GetMapping
    public ResponseEntity<RequestResultDto> getAllTrainingTypes(@PageableDefault Pageable pageable) {
        try {
            var result = exerciseTypeService.listAllTrainingTypes(pageable);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(result, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<RequestResultDto> getTrainingTypeById(@PathVariable Integer id) {
        try {
            var result = exerciseTypeService.listTrainingType(id);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(result, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<RequestResultDto> createTrainingType(@RequestBody ExerciseTypeInput input) {
        try {
            exerciseTypeService.addTrainingType(input);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<RequestResultDto> updateTrainingType(@RequestBody ExerciseTypeInput input, @PathVariable Integer id) {
        try {
            exerciseTypeService.updateTrainingType(input, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RequestResultDto> deleteTrainingType(@PathVariable Integer id) {
        try {
            exerciseTypeService.deleteTrainingType(id);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }
}
