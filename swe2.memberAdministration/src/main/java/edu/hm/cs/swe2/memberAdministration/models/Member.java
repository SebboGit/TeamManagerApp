package edu.hm.cs.swe2.memberAdministration.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(length = 50)
    private String firstname;
    @Column(length = 50)
    private String lastname;
    private LocalDate birth;
    private String description;
    private LocalDate entryDate;
    private LocalDate leavingDate;
    //private Integer teamId;
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer addressID;
    //@Lob
    //private byte[] photo;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    public Member(String firstname, String lastname, LocalDate birth, String description, LocalDate entryDate,
                  LocalDate leavingDate, Integer addressID, Address address){
        this.firstname = firstname;
        this.lastname = lastname;
        this.birth = birth;
        this.description = description;
        this.entryDate = entryDate;
        this.leavingDate = leavingDate;
        this.addressID = addressID;
        //this.photo = photo;
        this.address = address;
    }

    public Member() {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public LocalDate getBirth() { return birth; }
    public void setBirth(LocalDate birth) {this.birth = birth; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Address getAddress() {return address; }
    public void setAddress(Address address) {this.address = address; }
    public LocalDate getEntryDate() {return entryDate; }
    public void setEntryDate(LocalDate entryDate) {this.entryDate = entryDate; }
    public LocalDate getLeavingDate() {return leavingDate; }
    public  void  setLeavingDate(LocalDate leavingDate) {this.leavingDate = leavingDate; }
    public Integer getAddressID() {return addressID; }
    public void setAddressID(Integer addressID) {this.addressID = addressID; }
    //public byte[] getPhoto() { return photo; }
    //public void setPhoto(byte[] photo) { this.photo = photo;
    }


