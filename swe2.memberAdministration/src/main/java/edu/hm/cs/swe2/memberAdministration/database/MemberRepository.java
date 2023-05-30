package edu.hm.cs.swe2.memberAdministration.database;

import edu.hm.cs.swe2.memberAdministration.models.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Integer> {

    @Query("select m from Member m WHERE m.lastname = ?1")
    public List<Member> getMemberByLastname(String lastname);

    @Query("select m from Member m WHERE m.leavingDate is null")
    List<Member> getActiveMembers();

    @Query("UPDATE Member SET lastname = ?1 WHERE id = ?2")
    void changeLastnameById(String lastname, Integer id);

}