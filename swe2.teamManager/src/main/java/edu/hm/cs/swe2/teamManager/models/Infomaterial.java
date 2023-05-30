package edu.hm.cs.swe2.teamManager.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;


/**
 * Entity representing a infomaterial either as Url or PDF
 *
 * @author Michael Fortenbacher
 */
@Entity
public class Infomaterial {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer id;

    @Column(nullable = false)
    @ApiModelProperty(example = "lineup")
    private String name;

    @Column(nullable = false)
    @ApiModelProperty(example = "Plan of the lineup for the text tournament")
    private String description;

    @Column(nullable = false)
    @ApiModelProperty(example = "https://www.testsite.com")
    private String url;

    @ApiModelProperty(hidden = true)
    private byte[] pdf;


    public Infomaterial(Integer id, String name, String description, String url, byte[] pdf) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.pdf = pdf;
    }

    public Infomaterial() {
    }


    //Getter and Setter
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    /**
     * overrides the standard toString()-method
     *
     * @return representation of a infomaterial-entity in a String
     */
    @Override
    public String toString() {
        return String.format("Infomaterial: id: %d, name: %s, description: %s, url: %s, pdf: pdf in byte[] will not be shown", this.id, this.description, this.url);
    }
}
