package edu.hm.cs.swe2.memberfees.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.hm.cs.swe2.memberfees.models.Fee;
import edu.hm.cs.swe2.memberfees.services.ChangeFeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Restcontroller responsible for all actions regarding the modifications of
 * fees.
 * 
 * @author Jonas Buse
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Api(description = "Endpoints for changing existing fees.", tags = { "Change Fee" })
@RequestMapping(path = "api/v1/fees")
public class ChangeFeeController {

    @Autowired
    private ChangeFeeService service;

    @Autowired
    private DataValidator validator;

    /**
     * Endpoint to replace a fee with a given id with new values.
     * 
     * @param id  - the id of the fee to be changed
     * @param fee - A new fee which will overwrite the old one.
     * @return HTTP Response 200 with the changed fee
     */
    @ApiOperation(value = "Replace an existing fee with new values.", notes = "${ChangeFeeController.replaceFee.notes}")
    @PutMapping(value = "{id}")
    public ResponseEntity<Fee> replaceFee(@PathVariable("id") Integer id, @RequestBody Fee fee) {

        this.validator.runAll(fee);

        return new ResponseEntity<Fee>(this.service.changeAllById(id, fee), HttpStatus.OK);
    }

    /**
     * Endpoint used for changing only specific values of a fee. Using the PATCH
     * request and the query parameters, one can change any number of attributes of
     * a given fee. But at least one attribute has to be changed.
     * 
     * @param id       - Integer of specific fee (required)
     * @param amount   - Integer of desired new amount (optional)
     * @param status   - String of desired new status (optional)
     * @param date     - String of desired new date in ISO 8601 (optional)
     * @param memberId - Integer of desired new member ID (optional)
     * @return HTTP 200 (with the modified fee), 404 or 400
     */
    @ApiOperation(value = "Change specific values of a fee.", notes = "${ChangeFeeController.changeFee.notes}")
    @PatchMapping(value = "{id}")
    public ResponseEntity<Fee> changeFee(@PathVariable("id") Integer id, @RequestParam(required = false) Integer amount,
            @RequestParam(required = false) String status, @RequestParam(required = false) String date,
            @RequestParam(required = false) Integer memberId) {

        Fee newFee = new Fee(date, amount, status, memberId);

        if (amount == null && date == null && status == null && memberId == null)
            throw new NullPointerException("No values were given.");

        try {
            this.validator.runAll(newFee);
        } catch (NullPointerException e) { // NOPMD
        }

        return new ResponseEntity<Fee>(this.service.changeAllById(id, newFee), HttpStatus.OK);
    }

}
