package br.com.trainer.trainerapi.controller;

import br.com.trainer.trainerapi.model.dto.MemberInputDto;
import br.com.trainer.trainerapi.model.dto.MemberResultDto;
import br.com.trainer.trainerapi.model.dto.RequestResultDto;
import br.com.trainer.trainerapi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    MemberService memberService;


    @GetMapping("/api/member")
    public ResponseEntity<RequestResultDto> getAllMembers() {
        try {
            List<MemberResultDto> resultDto = memberService.listAllMembers();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new RequestResultDto(resultDto, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @GetMapping("/api/member/{trainerId}")
    public ResponseEntity<RequestResultDto> getMembersByTrainer(@PathVariable Integer trainerId) {
        try {
            List<MemberResultDto> resultDtos = memberService.listMembersByTrainer(trainerId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new RequestResultDto(resultDtos, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @PostMapping("/api/member")
    public ResponseEntity<RequestResultDto> createMember(@RequestBody MemberInputDto member) {
        try {
            memberService.addMember(member);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @PutMapping("/api/member/{id}")
    public ResponseEntity<RequestResultDto> updateMember(@RequestBody MemberInputDto member, @PathVariable Integer id) {
        try {
            memberService.updateMember(member, id);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    public ResponseEntity<RequestResultDto> removeMember(@PathVariable Integer id) {
        try {
            memberService.deleteMember(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }
}
