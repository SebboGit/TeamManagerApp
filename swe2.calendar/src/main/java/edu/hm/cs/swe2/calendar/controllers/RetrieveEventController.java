package edu.hm.cs.swe2.calendar.controllers;

import edu.hm.cs.swe2.calendar.SpringFoxConfig;
import edu.hm.cs.swe2.calendar.models.Event;
import edu.hm.cs.swe2.calendar.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Rest Controller for retrieving events from the database.
 *
 * @author Sebastian Theimer
 */
@RestController
@RequestMapping(path = "api/calendar")
@CrossOrigin(origins = "http://localhost:3000")
@Api(tags = {SpringFoxConfig.TAG_RETRIEVE_EVENT})
public class RetrieveEventController {

  private final EventService service;
  private final DataValidator validator;

  @Autowired
  public RetrieveEventController(EventService service, DataValidator validator) {
    this.service = service;
    this.validator = validator;
  }

  /** @return all events in the database */
  @GetMapping
  @ApiOperation(value = "Get all events.")
  public List<Event> getAllEvents() {
    return service.findAllEvents();
  }

  /** @return the total number of events in the database */
  @GetMapping(value = "/num")
  @ApiOperation(value = "Get latest id from the database.")
  public int getLatestEventId() {
    return service.findLatestEventId();
  }

  /**
   * @param id auto generated primary database key
   * @return single event matching the given id
   */
  @GetMapping(value = "/id/{id}")
  @ApiOperation(value = "Get event by id.")
  public Event getEventById(@PathVariable int id) {
    Event event = service.findById(id);

    if (event == null) {
      throw new NoSuchElementException();
    }

    return event;
  }

  /**
   * @param name event name
   * @return single event matching the given event name
   */
  @GetMapping(value = "/event/{name}")
  @ApiOperation(value = "Get event by name.")
  public Event getEventByName(@PathVariable String name) {
    validator.checkName(name);

    Event event = service.findByEventName(name);
    if (event == null) {
      throw new NoSuchElementException("Could not find event with name " + name);
    }
    return event;
  }

  /**
   * @param date string representation of a date
   * @return list of all events on that date
   */
  @GetMapping(value = "/date/{date}")
  @ApiOperation(value = "Get list of events by date.")
  public List<Event> getEventByDate(@PathVariable String date) {
    validator.checkDate(date);
    return service.findByDate(date);
  }
}
