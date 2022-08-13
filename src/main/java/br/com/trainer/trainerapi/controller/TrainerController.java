package br.com.trainer.trainerapi.controller;

import br.com.trainer.trainerapi.model.dto.RequestResultDto;
import br.com.trainer.trainerapi.model.dto.TrainerResultDto;
import br.com.trainer.trainerapi.model.entity.Trainer;
import br.com.trainer.trainerapi.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @GetMapping("/api/trainer")
    public ResponseEntity<RequestResultDto> getAllTrainers() {
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(trainerService.listAllTrainers(), false, null));
    }

    @GetMapping("/api/trainer/{id}")
    public ResponseEntity<RequestResultDto> getTrainer(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(trainerService.listTrainer(id), false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @PostMapping("/api/trainer")
    public ResponseEntity<RequestResultDto> createTrainer(@RequestBody Trainer trainer) {
        try {
            trainerService.registerTrainer(trainer);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @PutMapping("/api/trainer/{id}")
    public ResponseEntity<RequestResultDto> updateTrainer(@RequestBody Trainer trainer, @PathVariable Integer id) {
        try {
            trainerService.updateTrainer(trainer, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @DeleteMapping("/api/trainer/{id}")
    public ResponseEntity<RequestResultDto> deleteTrainer(@PathVariable Integer id) {
        try {
            trainerService.deleteTrainer(id);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null,true, ex.getMessage()));
        }
    }
}
