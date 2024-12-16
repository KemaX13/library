package com.factoria.library.controller;

import com.factoria.library.model.Member;
import com.factoria.library.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //CRUD
    @PostMapping("/members")
    public ResponseEntity<Member> createMember(@RequestBody Member newMember) {
        try {
            Member createdMember = memberService.addMember(newMember);
            return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return memberService.getAll();
    }

    @DeleteMapping("/members/id/{id}")
    public void deleteMember(@PathVariable long id) {
        memberService.deleteMember(id);
    }

    @PutMapping("/members/id/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable long id, @RequestBody Member updatedMember) {
        try {
            Member member = memberService.updateMember(id, updatedMember);
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Filter
    @GetMapping("/members/id/{id}")
    public ResponseEntity<Member> findMemberById(@PathVariable long id) {
        Optional<Member> foundMember = memberService.findMemberById(id);

        return foundMember.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/members/name/{name}")
    public List<Member> findMemberByName(@PathVariable String name) {
        return memberService.findMemberByName(name);
    }
}

