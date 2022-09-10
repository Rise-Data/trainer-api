package br.com.trainer.trainerapi.controller;

import br.com.trainer.trainerapi.model.dto.RequestResultDto;
import br.com.trainer.trainerapi.model.dto.trainingType.TrainingTypeInput;
import br.com.trainer.trainerapi.service.TrainingTypeService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainingType")
public class TrainingTypeController {
    private final TrainingTypeService trainingTypeService;

    public TrainingTypeController(TrainingTypeService trainingTypeService) {
        this.trainingTypeService = trainingTypeService;
    }

    @GetMapping
    public ResponseEntity<RequestResultDto> getAllTrainingTypes(@PageableDefault Pageable pageable) {
        try {
            var result = trainingTypeService.listAllTrainingTypes(pageable);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(result, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<RequestResultDto> getTrainingTypeById(@PathVariable Integer id) {
        try {
            var result = trainingTypeService.listTrainingType(id);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(result, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<RequestResultDto> createTrainingType(@RequestBody TrainingTypeInput input) {
        try {
            trainingTypeService.addTrainingType(input);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<RequestResultDto> updateTrainingType(@RequestBody TrainingTypeInput input, @PathVariable Integer id) {
        try {
            trainingTypeService.updateTrainingType(input, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RequestResultDto> deleteTrainingType(Integer id) {
        try {
            trainingTypeService.deleteTrainingType(id);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }
}
