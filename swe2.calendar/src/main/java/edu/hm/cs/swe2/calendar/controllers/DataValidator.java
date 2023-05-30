package edu.hm.cs.swe2.calendar.controllers;

import edu.hm.cs.swe2.calendar.models.Event;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Data validator for event properties
 *
 * @author Sebastian Theimer
 */
@Component
public class DataValidator {

  public void checkDate(String date) {
    try {
      LocalDate.parse(
          date, DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT));
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException(String.format("'%s' is not a date!", date));
    } catch (NullPointerException e) {
      throw new NullPointerException("No date was given!");
    }
  }

  public void checkTime(String time) {
    if (!time.matches("^[0-2][0-9]:[0-5][0-9]$")) {
      throw new IllegalArgumentException(String.format("'%s' is not a valid time format!", time));
    }
  }

  public void checkName(String name) {
    if (name.isBlank())
      throw new IllegalArgumentException("Name must contain at least one character.");
  }

  public void checkDescription(String description) {
    if (description.isBlank())
      throw new IllegalArgumentException("Description must contain at least one character.");
  }

  public void checkNumOfPhotos(int numOfPhotos) {
    if (numOfPhotos < 0)
      throw new IllegalArgumentException("Number of photos must be at least zero.");
  }

  /**
   * checks if given byte array is an actual pdf format, does not check for valid file content
   *
   * <p>all pdf files start with the bytes '%PDF' which is checked in the first 4 bytes of code
   *
   * @param program byte array pdf data
   */
  public void checkProgram(byte[] program) {
    if (program == null) throw new NullPointerException();
    boolean failure = program.length < 4;
    if (program[0] != 0x25) failure = true;
    if (program[1] != 0x50) failure = true;
    if (program[2] != 0x44) failure = true;
    if (program[3] != 0x46) failure = true;

    if (failure) {
      throw new IllegalArgumentException("Not a PDF.");
    }
  }

  /**
   * runs all of the above checks, program might not been uploaded to an event, so it will only be
   * checked if it is present
   *
   * @param event event entity
   */
  public void runAll(Event event) {
    checkDate(event.getDate());
    checkTime(event.getTime());
    checkName(event.getName());
    checkDescription(event.getDescription());
    checkNumOfPhotos(event.getNumOfPhotos());
    if (event.getProgram() != null) {
      checkProgram(event.getProgram());
    }
  }
}
