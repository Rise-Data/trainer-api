package br.com.trainer.trainerapi.controller;

import br.com.trainer.trainerapi.exception.RowNotFoundException;
import br.com.trainer.trainerapi.model.dto.RequestResultDto;
import br.com.trainer.trainerapi.model.dto.trainerClass.TrainerClassInputDto;
import br.com.trainer.trainerapi.model.dto.trainerClass.TrainerClassUpdateInputDto;
import br.com.trainer.trainerapi.service.TrainerClassService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainerClass")
public class TrainerClassController {

    private final TrainerClassService trainerClassService;

    public TrainerClassController(TrainerClassService trainerClassService) {
        this.trainerClassService = trainerClassService;
    }

    @GetMapping
    public ResponseEntity<RequestResultDto> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(trainerClassService.listAll(pageable), false, null));
    }

    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<RequestResultDto> getByTrainer(@PageableDefault Pageable pageable, @PathVariable Integer trainerId) {
        try {
            var result = trainerClassService.listByTrainer(pageable, trainerId);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(result, false, null));
        } catch (RowNotFoundException rex) {
            rex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResultDto(null, true, rex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<RequestResultDto> getByMember(@PageableDefault Pageable pageable, @PathVariable Integer memberId) {
        try {
            var result = trainerClassService.listByMember(pageable, memberId);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(result, false, null));
        } catch (RowNotFoundException rex) {
            rex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResultDto(null, true, rex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<RequestResultDto> register(@RequestBody TrainerClassInputDto trainerClassInputDto) {
        try {
            trainerClassService.addTrainerClass(trainerClassInputDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RequestResultDto(null, false, null));
        } catch (RowNotFoundException rex) {
            rex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResultDto(null, true, rex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestResultDto> update(@RequestBody TrainerClassUpdateInputDto trainerClassUpdateInputDto, @PathVariable Integer id) {
        try {
            trainerClassService.updateTrainerClass(trainerClassUpdateInputDto, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RequestResultDto(null, false, null));
        } catch (RowNotFoundException rex) {
            rex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResultDto(null, true, rex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RequestResultDto> delete(@PathVariable Integer id) {
        try {
            trainerClassService.deleteTrainerClass(id);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(null, false, null));
        } catch (RowNotFoundException rex) {
            rex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResultDto(null, true, rex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }
}
