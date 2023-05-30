package edu.hm.cs.swe2.memberfees.controllers;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.hm.cs.swe2.memberfees.services.DeleteFeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST Controller for all actions related to the removal of fees.
 * 
 * @author Jonas Buse
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Api(description = "Endpoint for deleting existing fees.", tags = { "Delete Fee" })
@RequestMapping(path = "api/v1/fees")
public class DeleteFeeController {

    @Autowired
    private DeleteFeeService service;

    @Autowired
    private DataValidator validator;

    /**
     * e Endpoint to delete a fee with a given id.
     * 
     * @param id Integer id of the fee
     * @throws NoSuchElementException if the given id doesnt exist
     */
    @ApiOperation(value = "Delete a fee.", notes = "${DeleteFeeController.deleteFee.notes}")
    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteFee(@PathVariable("id") Integer id) {
        validator.checkInt(id, "id");

        try {
            this.service.deleteFee(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException();
        }

        return new ResponseEntity<String>(String.format("Fee '%d' successfully deleted.", id), HttpStatus.OK);
    }

}
