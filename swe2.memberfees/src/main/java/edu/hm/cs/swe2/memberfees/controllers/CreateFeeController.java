package edu.hm.cs.swe2.memberfees.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.hm.cs.swe2.memberfees.models.Fee;
import edu.hm.cs.swe2.memberfees.services.CreateFeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Rest Controller for creating new memberfees.
 * 
 * @author Jonas Buse
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Api(description = "Endpoint for creating new fees.", tags = { "Create Fees" })
@RequestMapping(path = "api/v1/fees")
public class CreateFeeController {

    @Autowired
    private CreateFeeService service;

    @Autowired
    private DataValidator validator;

    /**
     * Endpoint to add one or multiple memberfees using a POST method. An array of
     * fees (one works too) with the following attributes is expected: date (ISO
     * 8601), amount, status, memberID.
     * 
     * @param fees Array of all new fees. No value can be null.
     * @return HTTP Response 201 with the created elements
     */
    @ApiOperation(value = "Create new fees.", notes = "${CreateFeeController.createFee.notes}")
    @PostMapping
    public ResponseEntity<List<Fee>> createFee(@RequestBody Fee... fees) {
        List<Fee> feesList = Arrays.asList(fees);

        if (feesList.isEmpty())
            throw new IllegalArgumentException("No element was given.");

        for (Fee e : fees)
            validator.runAll(e);

        return new ResponseEntity<List<Fee>>(this.service.saveFees(feesList), HttpStatus.CREATED);
    }

}
