package edu.hm.cs.swe2.memberAdministration.service;

import com.sun.xml.bind.v2.model.core.ID;
import edu.hm.cs.swe2.memberAdministration.database.MemberRepository;
import edu.hm.cs.swe2.memberAdministration.models.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepo;

    public Integer createMember(Member member) {
        Member newMember = memberRepo.save(member);
        return newMember.getId();
    }

    public Iterable<Member> getAllMembers() {
        return memberRepo.findAll();
    }

    public Optional<Member> getMemberById(Integer id) {
        return memberRepo.findById(id);
    }

    public List<Member> getMemberByLastname(String lastname) {
        return memberRepo.getMemberByLastname(lastname);
    }

    public void deleteById(Integer id) {
        memberRepo.deleteById(id);
    }

    public Iterable<Member> getActiveMembers() {
        return memberRepo.getActiveMembers();
    }

    public void changeLastnameById(Integer id, String lastname) {
        memberRepo.changeLastnameById(lastname, id);
        memberRepo.findById(id).get();
    }

}