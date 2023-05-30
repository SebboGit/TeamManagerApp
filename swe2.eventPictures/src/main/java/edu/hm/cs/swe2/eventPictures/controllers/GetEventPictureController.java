package edu.hm.cs.swe2.eventPictures.controllers;

import edu.hm.cs.swe2.eventPictures.database.EventPictureService;
import edu.hm.cs.swe2.eventPictures.models.EventPicture;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * REST API for getting the file and information from the PostgreSQL database
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
@RestController
@RequestMapping(path = "api/v1/EventPictures")
@Api(description = "Interface for Getting a Event Picture", tags = {"Get event picture"})
@CrossOrigin(origins = "http://localhost:3000")
public class GetEventPictureController {

    @Autowired
    private EventPictureService eventPictureService;

    @Autowired
    private DataValidation validation;

    /**
     * This method gets all the Event Pictures stored in the database.
     * If the database is empty an Exception with a message will be thrown.
     *
     * @return List of all Event Pictures
     * @throws NoSuchElementException when the database is empty
     */
    @ApiOperation(value = "get all Event Picture from the database", notes = "${getAllEventPictures.note}")
    @GetMapping(value = "getAll")
    public List<EventPicture> getAllEventPictures() throws NoSuchElementException {
        List<EventPicture> eventPictureList = eventPictureService.getAllEventPicture();

        if (eventPictureList.isEmpty()) {
            throw new NoSuchElementException("The database is empty!");
        }

        return eventPictureList;
    }

    /**
     * This method gets an Event Picture with the given ID stored in the database.
     * If the database is empty or the given ID is not in there, an Exception with a message will be thrown.
     *
     * @param id ID of an Event Picture from the Client side
     * @return an Event Picture with given ID
     */
    @ApiOperation(value = "get an Event Picture via existing ID", notes = "${getEventPicture.note}")
    @GetMapping(value = "get/{id}")
    public EventPicture getEventPicture(@PathVariable int id) {
        validation.validateId(id);

        if (!eventPictureService.checkEventPictureId(id)) {
            throw new NoSuchElementException("You have to enter a valid ID number. Either the value of the ID number was to high or the database is empty!");
        }
        return eventPictureService.getEventPicture(id);
    }

    /**
     * This method gets all Event Pictures with the given event ID stored in the database.
     * If the database is empty or the given event ID is not in there, an Exception with a message will be thrown.
     *
     * @param eventId ID of an event from the Client side
     * @return List of Event Pictures which have the given ID
     */
    @ApiOperation(value = "get an Event Picture via existing event ID", notes = "${getEventPictureEvent.note}")
    @GetMapping(value = "getByEvent/{eventId}")
    public List<EventPicture> getEventPictureByEvent(@PathVariable int eventId) {
        validation.validateId(eventId);

        if (!eventPictureService.checkEventPictureEventId(eventId)) {
            throw new NoSuchElementException("You have to enter a valid event ID number. Either the value of the event ID number was not present in the database or the database is empty!");
        }

        return eventPictureService.getByEventId(eventId);
    }

    /**
     * This method gets all Event Pictures with the given title stored in the database.
     * If the database is empty or the given title is not in there, an Exception with a message will be thrown.
     *
     * @param title title from the Client side
     * @return List of Event Pictures which have the given title
     */
    @ApiOperation(value = "get an Event Picture via the title", notes = "${getEventPictureTitle.note}")
    @GetMapping(value = "getByTitle/{title}")
    public List<EventPicture> getEventPictureByTitle(@PathVariable String title) {

        if (!eventPictureService.checkEventPictureTitle(title)) {
            throw new NoSuchElementException("You have to enter a valid title. Either the value of the title was not present in the database or the database is empty!");
        }

        return eventPictureService.getByTitle(title);
    }

    /**
     * This method gets all Event Pictures with the given description stored in the database.
     * If the database is empty or the given description is not in there, an Exception with a message will be thrown.
     *
     * @param description description from the Client side
     * @return List of Event Pictures which have the given description
     */
    @ApiOperation(value = "get an Event Picture via the description", notes = "${getEventPictureDescription.note}")
    @GetMapping(value = "getByDescription/{description}")
    public List<EventPicture> getEventPictureByDescription(@PathVariable String description) {

        if (!eventPictureService.checkEventPictureDescription(description)) {
            throw new NoSuchElementException("You have to enter a valid description. Either the value of the description was not present in the database or the database is empty!");
        }

        return eventPictureService.getByDescription(description);
    }

    /**
     * This method gets all Event Pictures with the given rating stored in the database.
     * If the database is empty or the given rating is not in there, an Exception with a message will be thrown.
     *
     * @param rating rating from the Client side
     * @return List of Event Pictures which have the given rating
     */
    @ApiOperation(value = "get an Event Picture via the rating", notes = "${getEventPictureRating.note}")
    @GetMapping(value = "getByRating/{rating}")
    public List<EventPicture> getEventPictureByRating(@PathVariable int rating) {
        validation.falseRatingNumber(rating);

        if (!eventPictureService.checkEventPictureRating(rating)) {
            throw new NoSuchElementException("You have to enter a valid rating. Either the value of the rating was not present in the database or the database is empty!");
        }

        return eventPictureService.getByRating(rating);
    }
}
