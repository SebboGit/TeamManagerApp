package edu.hm.cs.swe2.eventPictures.controllers;

import edu.hm.cs.swe2.eventPictures.database.EventPictureService;
import edu.hm.cs.swe2.eventPictures.models.EventPicture;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * REST API for uploading the file and information to the PostgreSQL database
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
@RestController
@RequestMapping(path = "api/v1/EventPictures")
@Api(description = "Interface for creating a Event Picture", tags = {"Create event picture"})
@CrossOrigin(origins = "http://localhost:3000")
public class CreateEventPictureController {

    @Autowired
    private EventPictureService eventPictureService;

    @Autowired
    private DataValidation validation;

    /**
     * This method creates a new input to the database.
     * It validates the parameter values from the client with the DataValidation.class.
     * If there was no Errors/Exceptions thrown, the values will be added to the database and a ResponseEntity will be returned.
     *
     * @param eventPictureNew values of the information
     * @param result          binds and contains errors that may have occured
     * @param image           image file to upload
     * @return the uploaded Event Picture and HTTP status
     * @throws IOException        throw exception when something is wrong with the input
     * @throws MultipartException the file upload could throw exception
     */
    @ApiOperation(value = "create a new event picture with an image and information", notes = "${createEventPicture.note}")
    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EventPicture> uploadEventPicture(@ModelAttribute EventPicture eventPictureNew, BindingResult result, @RequestPart(value = "file") MultipartFile image) throws IOException, MultipartException {
        validation.bindingFailure(result);
        if (eventPictureNew.getRating() != null) {
            validation.falseRatingNumber(eventPictureNew.getRating());
        }

        eventPictureService.uploadEventPicture(eventPictureNew, image);
        return new ResponseEntity<>(eventPictureNew, HttpStatus.CREATED);
    }

}
