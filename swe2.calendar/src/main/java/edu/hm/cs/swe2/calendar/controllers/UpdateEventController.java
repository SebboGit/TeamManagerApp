package edu.hm.cs.swe2.calendar.controllers;

import edu.hm.cs.swe2.calendar.SpringFoxConfig;
import edu.hm.cs.swe2.calendar.models.Event;
import edu.hm.cs.swe2.calendar.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller for updating events from the database.
 *
 * @author Sebastian Theimer
 */
@RestController
@RequestMapping(path = "api/calendar")
@CrossOrigin(origins = "http://localhost:3000")
@Api(tags = {SpringFoxConfig.TAG_UPDATE_EVENT})
public class UpdateEventController {

  private final EventService service;
  private final DataValidator validator;

  @Autowired
  public UpdateEventController(EventService service, DataValidator validator) {
    this.service = service;
    this.validator = validator;
  }

  /**
   * Updates one or more values of a specific event. Since you would usually only change one or two
   * values of an already existing event, it is fine to have multiple update calls to the database.
   *
   * @param id path variable for event to change
   * @param name optional parameter to be changed
   * @param description optional parameter to be changed
   * @param date optional parameter to be changed
   * @param time optional parameter to be changed
   * @return Response Entity
   */
  @ApiOperation(value = "Change an existing event.")
  @PatchMapping(value = "/id/{id}")
  @Transactional
  public ResponseEntity<Event> updateFee(
      @PathVariable int id,
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "description", required = false) String description,
      @RequestParam(name = "date", required = false) String date,
      @RequestParam(name = "time", required = false) String time) {

    boolean didUpdate = false;
    Event result = null;

    if (name != null) {
      validator.checkName(name);
      result = service.updateEventNameById(name, id);
      didUpdate = true;
    }

    if (description != null) {
      validator.checkDescription(description);
      result = service.updateEventDescriptionById(description, id);
      didUpdate = true;
    }

    if (date != null) {
      validator.checkDate(date);
      result = service.updateEventDateById(date, id);
      didUpdate = true;
    }

    // a time format in a query string cannot contain a colon so we have to add it here
    if (time != null) {
      time = time.substring(0, 2) + ":" + time.substring(2);
      validator.checkTime(time);
      result = service.updateEventTimeById(time, id);
      didUpdate = true;
    }

    if (didUpdate && result != null) {
      return new ResponseEntity<>(result, HttpStatus.OK);
    } else {
      throw new IllegalArgumentException("You need to pass one parameter to change.");
    }
  }
}
