package edu.hm.cs.swe2.calendar.controllers;

import edu.hm.cs.swe2.calendar.SpringFoxConfig;
import edu.hm.cs.swe2.calendar.models.Event;
import edu.hm.cs.swe2.calendar.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Rest Controller for creating events from the database.
 *
 * @author Sebastian Theimer
 */
@RestController
@RequestMapping(path = "api/calendar")
@CrossOrigin(origins = "http://localhost:3000")
@Api(tags = {SpringFoxConfig.TAG_CREATE_EVENT})
public class CreateEventController {

  private final EventService service;
  private final DataValidator validator;

  @Autowired
  public CreateEventController(EventService service, DataValidator validator) {
    this.service = service;
    this.validator = validator;
  }

  @ApiOperation(value = "Create new events.")
  @PostMapping
  public ResponseEntity<List<Event>> createEvent(@RequestBody Event... events) {
    List<Event> eventList = Arrays.asList(events);

    if (eventList.isEmpty()) {
      throw new IllegalArgumentException("No event was passed.");
    }

    for (Event event : eventList) {
      validator.runAll(event);
    }

    return new ResponseEntity<>(this.service.saveEvents(eventList), HttpStatus.CREATED);
  }

  /**
   * adds a pdf file to an existing event in the database
   *
   * @param id database id of the event that should be attached with a pdf
   * @param pdf the pdf file
   * @return ResponseEntity
   * @throws IOException getBytes might throw exception
   */
  @ApiOperation(value = "Add a pdf to an event.")
  @PostMapping(value = "/upload/id/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Event> uploadPDF(
      @PathVariable("id") int id, @RequestPart(value = "file") MultipartFile pdf)
      throws IOException {
    validator.checkProgram(pdf.getBytes());
    Event event = service.findById(id);
    service.uploadPDF(event, pdf);
    return new ResponseEntity<>(event, HttpStatus.OK);
  }
}
