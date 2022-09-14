package br.com.trainer.trainerapi.controller;

import br.com.trainer.trainerapi.model.dto.member.MemberInputDto;
import br.com.trainer.trainerapi.model.dto.member.MemberResultDto;
import br.com.trainer.trainerapi.model.dto.RequestResultDto;
import br.com.trainer.trainerapi.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<RequestResultDto> getAllMembers(@PageableDefault Pageable pageable) {
        try {
            Page<MemberResultDto> result = memberService.listAllMembers(pageable);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(result, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @GetMapping("{trainerId}")
    public ResponseEntity<RequestResultDto> getMembersByTrainer(@PageableDefault Pageable pageable, @PathVariable Integer trainerId) {
        try {
            Page<MemberResultDto> resultDtos = memberService.listMembersByTrainer(pageable, trainerId);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(resultDtos, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<RequestResultDto> createMember(@RequestBody MemberInputDto member) {
        try {
            memberService.addMember(member);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<RequestResultDto> updateMember(@RequestBody MemberInputDto member, @PathVariable Integer id) {
        try {
            memberService.updateMember(member, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RequestResultDto> removeMember(@PathVariable Integer id) {
        try {
            memberService.deleteMember(id);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResultDto(null, false, null));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResultDto(null, true, ex.getMessage()));
        }
    }
}
