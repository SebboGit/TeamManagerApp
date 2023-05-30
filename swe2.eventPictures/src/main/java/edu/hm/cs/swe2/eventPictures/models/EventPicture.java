package edu.hm.cs.swe2.eventPictures.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

/**
 * this Entity class creates a relation in the database
 * and also includes Getters and Setters
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
@Entity
public class EventPicture {

    /*
       this attribute is creates an ID automatically
       and is not updatable, because the Id should be unique and not changeable
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    @ApiModelProperty(hidden = true)
    private Integer id;

    @ApiModelProperty(example = "anniversary celebration")
    private String title;

    @ApiModelProperty(example = "It was very nice")
    private String description;

    @ApiModelProperty(example = "4")
    @Min(1)
    @Max(5)
    private Integer rating;

    // this is a String, because at the point only one value can be stored in this column
    @ApiModelProperty(example = "1, 34")
    private String members;

    @ApiModelProperty(example = "1")
    private Integer event;

    @ApiModelProperty(hidden = true)
    private String picName;

    @ApiModelProperty(hidden = true)
    private String picType;

    @ApiModelProperty(hidden = true)
    private byte[] picData;

    public EventPicture(String title, String description, Integer rating, String members, Integer event, String picName, String picType, byte[] picData) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.members = members;
        this.event = event;
        this.picName = picName;
        this.picType = picType;
        this.picData = picData;
    }

    public EventPicture(String title, String description, Integer rating, String members, Integer event) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.members = members;
        this.event = event;
    }

    public EventPicture(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public EventPicture(String title, String description, int rating, int event) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.event = event;
    }

    public EventPicture() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * this Getter for the attribute 'members' creates a new List<Integer>
     * and also a variable 'memberId' for storing the individual IDs.
     * If the attribute 'members' is not null, then a for-loop is called.
     * In the for-loop the String of the attribute 'members' is split into the individual IDs
     * and after that stored in the variable 'lstMembers'.
     *
     * @return is the variable 'lstMembers' with all IDs, which are stored in the attribute 'members'
     */
    public List<Integer> getMembers() {
        List<Integer> lstMembers = new ArrayList<>();
        int memberId = 0;
        if (this.members != null) {
            for (String id : this.members.split(", ")) {
                memberId = Integer.parseInt(id);
                lstMembers.add(memberId);
            }
        }
        return lstMembers;
    }

    /**
     * this Setter for the attribute 'members' declares a StringBuilder 'newMembers'
     * to create a String out of the List<Integer>.
     * If the Integer list (param) is not empty, a for-loop iterates over the param 'members' and append the individual IDs to 'newMembers'
     * and also appends a comma after every ID.
     * The last comma is deleted after the for-loop and the finished String stored in the Attribute 'members'.
     *
     * @param members the List of Integer IDs, which should be stored in the table
     */
    public void setMembers(List<Integer> members) {
        StringBuilder newMembers = new StringBuilder();
        if (!members.isEmpty()) {
            for (Integer i : members) {
                newMembers.append(i);
                newMembers.append(", ");
            }
            newMembers.deleteCharAt(newMembers.length() - 1);
            newMembers.deleteCharAt(newMembers.length() - 1);
            this.members = newMembers.toString();
        }
    }

    public Integer getEvent() {
        return event;
    }

    public void setEvent(Integer event) {
        this.event = event;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType;
    }

    public byte[] getPicData() {
        return picData;
    }

    public void setPicData(byte[] picData) {
        this.picData = picData;
    }

    @Override
    public String toString() {
        return String.format("\npicture name: \t%s\n" +
                        "title: \t\t\t%s\n" +
                        "description: \t%s\n" +
                        "event: \t\t\t%d\n" +
                        "rating: \t\t%d\n" +
                        "members: \t\t%s",
                picName, title, description, event, rating, members);
    }
}
