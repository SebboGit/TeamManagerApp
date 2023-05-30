package edu.hm.cs.swe2.eventPictures.controllers;

import edu.hm.cs.swe2.eventPictures.database.EventPictureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

/**
 * REST API for deleting the file and information from the PostgreSQL database
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
@RestController
@RequestMapping(path = "api/v1/EventPictures")
@Api(description = "Interface for Deleting a Event Picture", tags = {"Delete event picture"})
@CrossOrigin(origins = "http://localhost:3000")
public class DeleteEventPictureController {

    @Autowired
    private EventPictureService eventPictureService;

    @Autowired
    private DataValidation validation;

    /**
     * This method deletes a event picture.
     * It takes the ID, which should be deleted, out of the URL and calls the ValidateId() method from the DataValidation.class.
     * Then the method to delete the event picture with the ID is called.
     *
     * @param id ID which should be deleted
     * @return ResponseEntity gives a String back with the information and the HttpStatus
     */
    @ApiOperation(value = "delete a Event Picture via existing ID", notes = "${deleteEventPicture.note}")
    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<String> deleteEventPicture(@PathVariable int id) {
        validation.validateId(id);

        if (!eventPictureService.checkEventPictureId(id)) {
            throw new NoSuchElementException("You have to enter a valid ID number. Either the value of the ID number was to high or the database is empty!");
        }

        eventPictureService.deleteEventPicture(id);
        return new ResponseEntity<>(String.format("Event Picture with id '%d' deleted.", id), HttpStatus.OK);
    }

}
