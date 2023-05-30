package edu.hm.cs.swe2.calendar.controllers;

import edu.hm.cs.swe2.calendar.SpringFoxConfig;
import edu.hm.cs.swe2.calendar.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

/**
 * Rest Controller for deleting events from the database.
 *
 * @author Sebastian Theimer
 */
@RestController
@RequestMapping(path = "api/calendar")
@CrossOrigin(origins = "http://localhost:3000")
@Api(tags = {SpringFoxConfig.TAG_DELETE_EVENT})
public class DeleteEventController {

  private final EventService service;

  @Autowired
  public DeleteEventController(EventService service) {
    this.service = service;
  }

  /**
   * @param id auto generated primary database key
   * @return {@link ResponseEntity} with status okay
   */
  @DeleteMapping(value = "/id/{id}")
  @ApiOperation(value = "Delete event by id.")
  public ResponseEntity<String> deleteEvent(@PathVariable("id") int id) {
    try {
      service.deleteEvent(id);
    } catch (EmptyResultDataAccessException e) {
      throw new NoSuchElementException();
    }
    return new ResponseEntity<>(String.format("Event No. %d deleted.", id), HttpStatus.OK);
  }
}
