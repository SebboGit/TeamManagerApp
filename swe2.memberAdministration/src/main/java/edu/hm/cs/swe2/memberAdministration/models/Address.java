package edu.hm.cs.swe2.memberAdministration.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    private String street;
    private Integer houseNumber;
    private String city;

    public Address() {
    }

    public Address(String street, Integer houseNumber, String city){
        super();
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getStreet() { return street; }
    public void setStreet(String street) {this.street = street; }
    public Integer getHouseNumber() { return houseNumber; }
    public void setHouseNumber(Integer houseNumber) { this.houseNumber = houseNumber; }
    public String getCity() { return city; }
    public  void setCity(String city) { this.city = city; }

}
