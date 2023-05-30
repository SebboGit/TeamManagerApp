package edu.hm.cs.swe2.memberfees.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Column;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

/**
 * Entity representing a singular memberfee. One member can have multiple fees
 * associated.
 * 
 * @author Jonas Buse
 */
@Entity
public class Fee { // NOPMD

    @Id
    @GeneratedValue(strategy = AUTO)
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @Column(nullable = false)
    @ApiModelProperty(example = "2021-01-29", required = true, value = "ISO 8601 formatted date.")
    private String date;

    @Column(nullable = false)
    @ApiModelProperty(example = "10", required = true)
    private Integer amount;

    @Column(nullable = false)
    @ApiModelProperty(example = "pending", required = true, allowableValues = "pending, confirmed")
    private String status;

    @Column(nullable = false)
    @ApiModelProperty(example = "5", required = true)
    private Integer memberId;

    public Fee(String date, Integer amount, String status, Integer memberId) {
        this.date = date;
        this.amount = amount;
        this.status = status;
        this.memberId = memberId;
    }

    public Fee() { // NOPMD

    }

    public Integer getId() {
        return this.id;
    }

    public String getDate() {
        return date;
    }

    public Fee setDate(String date) {
        this.date = date;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public Fee setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Fee setStatus(String status) {
        this.status = status;
        return this;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public Fee setMemberId(Integer memberId) {
        this.memberId = memberId;
        return this;
    }

    /**
     * Better visual representation of the fee if printed as a String.
     */
    @Override
    public String toString() {
        return String.format("Fee %d: date=%s, amount=%d, status=%s, memberId=%d", this.id, this.date, this.amount,
                this.status, this.memberId);
    }

}
