package edu.hm.cs.swe2.calendar.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * Event entity representing an event
 *
 * @author Sebastian Theimer
 */
@Entity
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @ApiModelProperty(value = "id", hidden = true)
  private int id;

  @Column(nullable = false)
  @ApiModelProperty(example = "2021-07-20", required = true, value = "ISO 8601 formatted date.")
  private String date;

  @Column(nullable = false)
  @ApiModelProperty(example = "13:00", required = true, value = "24 hour time format, 4 digits")
  private String time;

  @Column(nullable = false)
  @ApiModelProperty(required = true)
  private String name;

  @Column(nullable = false)
  @ApiModelProperty(required = true)
  private String description;

  @Column(nullable = false)
  @ApiModelProperty(example = "3", required = true, value = "Can be zero but not null.")
  private int numOfPhotos;

  @Column
  @ApiModelProperty(value = "PDF program of event.")
  private byte[] program;

  public Event() {}

  public Event(String date, String time, String name, String description, int numOfPhotos) {
    this(date, time, name, description, numOfPhotos, new byte[] {});
  }

  public Event(
      String date, String time, String name, String description, int numOfPhotos, byte[] program) {
    this.date = date;
    this.time = time;
    this.name = name;
    this.description = description;
    this.numOfPhotos = numOfPhotos;
    this.program = program;
  }

  public Integer getId() {
    return id;
  }

  public String getDate() {
    return date;
  }

  public String getTime() {
    return time;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public int getNumOfPhotos() {
    return numOfPhotos;
  }

  public byte[] getProgram() {
    return program;
  }

  public Event setId(Integer id) {
    this.id = id;
    return this;
  }

  public Event setDate(String date) {
    this.date = date;
    return this;
  }

  public Event setName(String name) {
    this.name = name;
    return this;
  }

  public Event setDescription(String description) {
    this.description = description;
    return this;
  }

  public Event setNumOfPhotos(int numOfPhotos) {
    this.numOfPhotos = numOfPhotos;
    return this;
  }

  public Event setProgram(byte[] program) {
    this.program = program;
    return this;
  }

  @Override
  public String toString() {
    return String.format(
        "Event %s No. %d at %s%nDescription: %s%nNumber of photos: %d",
        name, id, date, description, numOfPhotos);
  }
}
