package edu.hm.cs.swe2.memberfees.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.hm.cs.swe2.memberfees.models.Fee;
import edu.hm.cs.swe2.memberfees.services.CalculateFeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST-Controller responsible for calculating new fees for all members.
 * 
 * @author Jonas Buse
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Api(description = "Endpoints for automatically calculating new Fees.", tags = { "Calculate Fees" })
@RequestMapping(path = "api/v1/fees/calculate")
public class CalculateFeeController {

    @Autowired
    private CalculateFeeService service;

    @Autowired
    private DataValidator validator;

    /**
     * Calculate new fees for all members for a given year. This does not save them
     * to the DB however.
     */
    @ApiOperation(value = "Calculate new Fees for all members for a year.", notes = "${CalculateFeeController.calculateFees.notes}")
    @GetMapping(value = "{year}")
    public ResponseEntity<List<Fee>> calculateFees(@PathVariable("year") Integer year) {

        validator.checkInt(year, "year");

        return new ResponseEntity<List<Fee>>(this.service.calculateFees(year, false), HttpStatus.OK);
    }

    /**
     * Calculate new fees for all members for the current year. his does not save
     * them to the DB however.
     */
    @ApiOperation(value = "Calculate new Fees for all members for this year.", notes = "${CalculateFeeController.calculateFeesThisYear.notes}")
    @GetMapping
    public ResponseEntity<List<Fee>> calculateFeesThisYear() {

        return new ResponseEntity<List<Fee>>(this.service.calculateFees(LocalDate.now().getYear(), false),
                HttpStatus.OK);
    }

    /**
     * Calculate new fees for all members for a given year and then save the new
     * Fees to the Database.
     */
    @ApiOperation(value = "Calculate new Fees for all members for a year and save them.", notes = "${CalculateFeeController.calculateFeesAndSave.notes}")
    @PostMapping(value = "{year}")
    public ResponseEntity<List<Fee>> calculateFeesAndSave(@PathVariable("year") Integer year) {

        validator.checkInt(year, "year");

        return new ResponseEntity<List<Fee>>(this.service.calculateFees(year, true), HttpStatus.CREATED);
    }
}