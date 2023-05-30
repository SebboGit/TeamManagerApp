package edu.hm.cs.swe2.eventPictures.controllers;

import edu.hm.cs.swe2.eventPictures.database.EventPictureService;
import edu.hm.cs.swe2.eventPictures.models.EventPicture;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * REST API for updating the file and information in the PostgreSQL database
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
@RestController
@RequestMapping(path = "api/v1/EventPictures")
@Api(description = "Interface for updating a Event Picture", tags = {"Update event picture"})
@CrossOrigin(origins = "http://localhost:3000")
public class PutEventPictureController {

    @Autowired
    private EventPictureService eventPictureService;

    @Autowired
    private DataValidation validation;


    /**
     * This method updates a present event picture in the database.
     * It validates the parameter values from the client with the DataValidation.class.
     * If there was no Errors/Exceptions thrown, the values will be updated in the database and a ResponseEntity will be returned.
     *
     * @param id              id to update
     * @param eventPictureNew new input values to update
     * @param result          binds and contains errors that may have occured
     * @param image           new image to update
     * @return a String and HTTP status
     * @throws IOException throw exception when something is wrong with the input
     */
    @ApiOperation(value = "change an Event Picture via existing ID", notes = "${putEventPicture.note}")
    @PutMapping(value = "update/{id}")
    public ResponseEntity<String> updateEventPicture(@PathVariable int id, @ModelAttribute EventPicture eventPictureNew, BindingResult result, @RequestPart(value = "file", required = false) MultipartFile image) throws IOException {
        validation.validateId(id);
        validation.bindingFailure(result);
        if (eventPictureNew.getRating() != null) {
            validation.falseRatingNumber(eventPictureNew.getRating());
        }

        if (!eventPictureService.checkEventPictureId(id)) {
            throw new NoSuchElementException("You have to enter a valid ID number. Either the value of the ID number was to high or the database is empty!");
        }

        eventPictureService.updateEventPicture(id, eventPictureNew, image);
        return new ResponseEntity<>(String.format("Event Picture with ID '%d' updated.", id), HttpStatus.OK);
    }

}
