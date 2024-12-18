package com.factoria.library.service;

import com.factoria.library.model.Member;
import com.factoria.library.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    public Member addMember(Member newMember) {
        return memberRepository.save(newMember);
    }

    public void deleteMember(long id) {
        memberRepository.deleteById(id);
    }

    public Optional<Member> findMemberById(long id) {
        return memberRepository.findById(id);
    }

    public Member updateMember(long id, Member updatedMember) {
        Optional<Member> foundMember = memberRepository.findById(id);

        if (foundMember.isPresent()) {
            Member existingMember = foundMember.get();

            existingMember.setName(updatedMember.getName());
            return memberRepository.save(existingMember);
        }

        throw new RuntimeException("Member not found with id: " + id);
    }

    public List<Member> findMemberByName(String name) {
        return memberRepository.findMemberByName(name);
    }
}
