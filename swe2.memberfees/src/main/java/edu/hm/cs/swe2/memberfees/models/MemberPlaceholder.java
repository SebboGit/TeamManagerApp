package edu.hm.cs.swe2.memberfees.models;

/**
 * Altered copy of the Member from the memberAdmin microservice, used as a
 * placeholder until the memberAdmin backend is working and can be implemented
 * to work with this microservice.
 * 
 * @author Jonas Buse
 */
public class MemberPlaceholder {

    private Integer id;
    private String firstname;
    private String lastname;
    private String entryDate;
    private String leavingDate;
    private int[] memberships;

    public MemberPlaceholder(Integer id, String firstname, String lastname, String entryDate, String leavingDate,
            int[] memberships) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.entryDate = entryDate;
        this.leavingDate = leavingDate;
        this.memberships = memberships;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(String leavingDate) {
        this.leavingDate = leavingDate;
    }

    public int[] getMemberships() {
        return memberships;
    }

    public void setMemberships(int[] memberships) {
        this.memberships = memberships;
    }

}
