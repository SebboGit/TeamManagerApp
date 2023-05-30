package edu.hm.cs.swe2.teamManager.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a single sports team
 *
 * @author Michael Fortenbacher
 */
@Entity
public class Team {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer id;

    @Column(nullable = false)
    @ApiModelProperty(example = "FC Mustermannschaft")
    private String name;

    @Column(nullable = false)
    @ApiModelProperty(hidden = true)
    private Integer memberCount;

    //String that stores all member ids in one string seperated by "," since Lists are not allowed here.
    @Column(nullable = false)
    @ApiModelProperty(example = "1,2,3,4")
    private String memberIds = "";


    @Column(nullable = false)
    @ApiModelProperty(hidden = true)
    private Integer infomaterialCount;

    @Column(nullable = false)
    @ApiModelProperty(example = "4,3,2,1")
    private String infomaterialIds = "";


    public Team(int id, String name, String memberIds, String infomaterialIds) {
        this.id = id;
        this.name = name;
        this.memberIds = memberIds;
        this.memberCount = getMemberCount();
        this.infomaterialIds = infomaterialIds;
        this.infomaterialCount = getInfomaterialCount();
    }

    public Team() {
    }

    /**
     * Getter-Method that returns the member-ids stored in an String as a List<Integer> by splitting the String after each "," and converting the individual Strings in Integers
     *
     * @return list of all member-ids as integers
     */
    public List<Integer> getMemberIdsInteger() {
        List<Integer> memberIdList = new ArrayList<>();
        if (memberIds != null && !memberIds.isEmpty()) {
            for (String id : this.memberIds.split(",")) {
                try {
                    int memberId = Integer.parseInt(id);
                    memberIdList.add(memberId);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return memberIdList;
    }

    public String getMemberIds() {
        return this.memberIds;
    }

    public void setMemberIds(String memberIds) {
        this.memberIds = memberIds;
        this.memberCount = getMemberCount();
    }


    /**
     * Getter-Method that returns the infomaterial-ids stored in an String as a List<Integer> by splitting the String after each "," and converting the individual Strings in Integers
     *
     * @return list of all member-ids as integers
     */
    public List<Integer> getInfomaterialIdsInteger() {
        List<Integer> infomaterialIdList = new ArrayList<>();
        if (infomaterialIds != null && !infomaterialIds.isEmpty()) {
            for (String id : this.infomaterialIds.split(",")) {
                try {
                    int infomaterialId = Integer.parseInt(id);
                    infomaterialIdList.add(infomaterialId);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return infomaterialIdList;
    }

    public String getInfomaterialIds() {
        return this.infomaterialIds;
    }

    public Integer getMemberCount() {
        return getMemberIdsInteger().size();
    }

    public Integer getInfomaterialCount() {
        return getInfomaterialIdsInteger().size();
    }

    public void setInfomaterialIds(String infomaterialIds) {
        this.infomaterialIds = infomaterialIds;
        this.infomaterialCount = getInfomaterialCount();
    }

    /**
     * @return number of member of this sports team
     */


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * overrides the standard toString()-method
     *
     * @return representation of a team-entity in a String
     */
    @Override
    public String toString() {
        return String.format("Team: id: %d, name: %s, memberIDs: %s, memberCount: %d, infomaterialId: %s, infomaterialCount: %d", this.id, this.name, this.memberIds, this.memberCount, this.infomaterialIds,
                this.infomaterialCount);
    }
}
