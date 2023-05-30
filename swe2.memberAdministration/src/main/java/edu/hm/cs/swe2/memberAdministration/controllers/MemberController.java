package edu.hm.cs.swe2.memberAdministration.controllers;

import edu.hm.cs.swe2.memberAdministration.models.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import edu.hm.cs.swe2.memberAdministration.service.MemberService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * REST controller to read, write and delete from database
 *
 * @author Michael Steinbacher
 */
@RestController
@RequestMapping("/api/v1/memberAdministration")
@CrossOrigin(origins = "http://localhost:3000")

public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * Read all existing members in database
     *
     * @return all members
     * @throws NoSuchElementException if no members are in database
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Member> getAllMembers() {
        List<Member> allMembers = (List<Member>) this.memberService.getAllMembers();

        if (allMembers.size() == 0)
            throw new NoSuchElementException("No elements were found");
        return allMembers;
    }

    /**
     * Read member with written id
     *
     * @param id
     * @return member
     */
    @RequestMapping(value = "/member/{id}", method = RequestMethod.GET)
    public Optional<Member> member(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new NoSuchElementException("Negative value");
        }
        return memberService.getMemberById(id);
    }

    /**
     * Write new member
     *
     * @param member
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Integer createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }

    /**
     * Delete member with written id
     *
     * @param id
     */
    @RequestMapping(value = "member/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new NoSuchElementException("Negative value");
        }
        memberService.deleteById(id);
    }

    /**
     * read all active members where leavingDate is null
     * @return
     */
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public List<Member> getActiveMembers() {
        List<Member> activeMembers = (List<Member>) this.memberService.getActiveMembers();
        if (activeMembers.size() == 0)
            throw new NoSuchElementException("No elements were found");
        return activeMembers;
    }

    /**
     * read member with given lastname
     * @param lastname
     * @return
     */
    @RequestMapping(value = "/lastname/{lastname}", method = RequestMethod.GET)
    public List<Member> member(@PathVariable("lastname") String lastname) {
        if (lastname == null) {
            throw new IllegalArgumentException("Please give a Name");
        }
        if (memberService.getMemberByLastname(lastname) == null) {
            throw new NoSuchElementException("No Members with this name");

        }
        return memberService.getMemberByLastname(lastname);
    }

   // @PatchMapping(value = "{id}")
    //public ResponseEntity<Member> changeMember(@PathVariable("id") Integer id, @RequestParam(required = false) String firstname,
     //                                          @RequestParam("lastname") String lastname, @RequestParam("birth") LocalDate birth,
       //                                        @RequestParam("description") String description, @RequestParam("entryDate") LocalDate entryDate,
         //                                      @RequestParam("leavingDate") LocalDate leavingDate, @RequestParam("addressID") Integer addressID,
           //                                    @RequestParam("address") Address address) {

       // Member newMember = new Member(firstname, lastname, birth, description, entryDate, leavingDate, addressID, address);

        //if (firstname == null && lastname == null && birth == null && description == null && entryDate == null && leavingDate == null
        //&& addressID == null && address == null)
          //  throw new NullPointerException("No values");

      //  return new ResponseEntity<Member>(this.memberService.changeLastnameById(id, lastname), HttpStatus.OK);
    // }

    @PutMapping("/change/{id}")
    public void changeLastnameById(@RequestParam String lastname, @PathVariable("id") Integer id) {
        memberService.changeLastnameById(id, lastname);
    }

}
