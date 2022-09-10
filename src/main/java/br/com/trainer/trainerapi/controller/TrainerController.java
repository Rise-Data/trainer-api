package br.com.trainer.trainerapi.controller;

import br.com.trainer.trainerapi.model.dto.RequestResultDto;
import br.com.trainer.trainerapi.model.dto.trainer.TrainerInputDto;
import br.com.trainer.trainerapi.model.dto.trainer.TrainerUpdatableInputDto;
import br.com.trainer.trainerapi.service.TrainerService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/api/trainer")
public class TrainerController {

    private final TrainerService trainerService;
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping
    public ResponseEntity<RequestResultDto> getAllTrainers(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(trainerService.listAllTrainers(pageable), false, null));
    }

    @GetMapping("{id}")
    public ResponseEntity<RequestResultDto> getTrainer(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(trainerService.listTrainer(id), false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<RequestResultDto> createTrainer(@RequestBody TrainerInputDto trainer) {
        try {
            trainerService.registerTrainer(trainer);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<RequestResultDto> updateTrainer(@RequestBody TrainerUpdatableInputDto trainer, @PathVariable Integer id) {
        try {
            trainerService.updateTrainer(trainer, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RequestResultDto> deleteTrainer(@PathVariable Integer id) {
        try {
            trainerService.deleteTrainer(id);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null,true, ex.getMessage()));
        }
    }
}
