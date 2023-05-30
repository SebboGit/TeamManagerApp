package edu.hm.cs.swe2.memberfees.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.hm.cs.swe2.memberfees.models.Fee;
import edu.hm.cs.swe2.memberfees.services.ReadFeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for reading from the databse.
 * 
 * @author Jonas Buse
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Api(description = "Endpoints for reading/finding existing fees.", tags = { "Read Fees" })
@RequestMapping(path = "api/v1/fees")
public class ReadFeeController {

    @Autowired
    private ReadFeeService service;

    @Autowired
    private DataValidator validator;

    /**
     * Read all existing fees from the database.
     * 
     * @return all fees.
     * @throws NoSuchElementException if no elements were found
     */
    @ApiOperation(value = "Get all fees.", notes = "${ReadFeeController.getAllFees.notes}")
    @GetMapping
    public List<Fee> getAllFees() {
        List<Fee> returnValue = this.service.findAll();

        if (returnValue.isEmpty())
            throw new NoSuchElementException();
        return returnValue;
    }

    /**
     * Read a specific fee with the given id as a parameter.
     * 
     * @param id the unique id of the fee
     * @return a single fee matching the id
     * @throws NoSuchElementException if the id doesnt exist
     */
    @ApiOperation(value = "Get a specific fee.", notes = "${ReadFeeController.getFeeById.notes}")
    @GetMapping(value = "{id}")
    public Fee getFeeById(@PathVariable("id") Integer id) {
        validator.checkInt(id, "id");

        return this.service.findById(id);
    }

    /**
     * Get all fees from a specific member.
     * 
     * @param id member id
     * @return List of fees
     * @throws NoSuchElementException if no elements were found
     */
    @ApiOperation(value = "Get all fees from a member.", notes = "${ReadFeeController.findAllByMemberId.notes}")
    @GetMapping(value = "member/{id}")
    public List<Fee> findByMemberId(@PathVariable("id") Integer id) {
        validator.checkInt(id, "member id");

        List<Fee> returnValue = this.service.findByMemberId(id);

        if (returnValue.isEmpty())
            throw new NoSuchElementException();
        return returnValue;
    }

    /**
     * Get all fees from a specific Date. Only works with ISO 8601 dates
     * (yyyy-mm-dd). Dates are checked to be of the right format.
     * 
     * @param date ISO 8601 date
     * @return List of fees
     * @throws NoSuchElementException if no elements were found
     */
    @ApiOperation(value = "Get all fees from a date.", notes = "${ReadFeeController.findByDate.notes}")
    @GetMapping(value = "date/{date}")
    public List<Fee> findByDate(@PathVariable("date") String date) {
        validator.checkDate(date);

        List<Fee> returnValue = this.service.findByDate(date);

        if (returnValue.isEmpty())
            throw new NoSuchElementException();
        return returnValue;
    }

    /**
     * Get all fees between two dates. 'Until' is optional. Only works with ISO 8601
     * dates (yyyy-mm-dd). Dates are checked to be of the right format.
     * 
     * @param from  ISO 8601 date 'from'
     * @param until (optional) ISO 8601 date 'until'
     * @return List of fees
     * @throws NoSuchElementException if no elements were found
     */
    @ApiOperation(value = "Get all fees from a range of dates.", notes = "${ReadFeeController.findByDateRange.notes}")
    @GetMapping(value = "date")
    public List<Fee> findByDateRange(@RequestParam("from") String min,
            @RequestParam(value = "until", required = false) String max) {

        max = (max == null) ? java.time.LocalDate.now().toString() : max; // NOPMD

        validator.checkDate(min);
        validator.checkDate(max);

        List<Fee> returnValue = this.service.findByDateRange(min, max);

        if (returnValue.isEmpty())
            throw new NoSuchElementException();
        return returnValue;
    }

    /**
     * Get all fees of a specific status.
     * 
     * @param status a status
     * @return a list of fees
     * @throws NoSuchElementException if no elements were found
     */
    @ApiOperation(value = "Get all fees with a specific status.", notes = "${ReadFeeController.findByStatus.notes}")
    @GetMapping(value = "status/{status}")
    public List<Fee> findByStatus(@PathVariable("status") String status) {
        validator.checkStatus(status);

        List<Fee> returnValue = this.service.findByStatus(status);

        if (returnValue.isEmpty())
            throw new NoSuchElementException();
        return returnValue;
    }

    /**
     * Get all fees of a specific amount.
     * 
     * @param amount Integer amount
     * @return List of fees
     * @throws NoSuchElementException if no elements were found
     */
    @ApiOperation(value = "Get all fees with a specific amount.", notes = "${ReadFeeController.findByAmount.notes}")
    @GetMapping(value = "amount/{amount}")
    public List<Fee> findByAmount(@PathVariable("amount") Integer amount) {
        validator.checkInt(amount, "amount");

        List<Fee> returnValue = this.service.findByAmount(amount);

        if (returnValue.isEmpty())
            throw new NoSuchElementException();

        return returnValue;
    }

}
