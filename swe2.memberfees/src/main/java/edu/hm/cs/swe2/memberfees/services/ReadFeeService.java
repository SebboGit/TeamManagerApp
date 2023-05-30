package edu.hm.cs.swe2.memberfees.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hm.cs.swe2.memberfees.database.FeeRepository;
import edu.hm.cs.swe2.memberfees.models.Fee;

/**
 * Service class to implement 'read' Repository functions.
 * 
 * @author Jonas Buse
 */
@Service
public class ReadFeeService {

    @Autowired
    private FeeRepository repo;

    /**
     * Read all existing fees from the database.
     * 
     * @return all fees.
     */
    public List<Fee> findAll() {
        return repo.findAll();
    }

    /**
     * Find a specific fee with the given ID and return it.
     * 
     * @param id the fee id
     * @return a single fee.
     * @throws NoSuchElementException if no elements were found
     */
    public Fee findById(Integer id) throws NoSuchElementException { // NOPMD
        return repo.findById(id).get();
    }

    /**
     * Get all fees from a specific member.
     * 
     * @param id member id
     * @return List of fees
     */
    public List<Fee> findByMemberId(Integer id) {
        return repo.findByMemberId(id);
    }

    /**
     * Get all fees from a specifc date. These need to be in the ISO 8601 standard
     * (yyyy-mm-dd).
     * 
     * @param date ISO 8601 date-string
     * @return List of fees
     */
    public List<Fee> findByDate(String date) {
        return repo.findByDate(date);
    }

    /**
     * Get all fees between two dates. These need to be in the ISO 8601 standard
     * (yyyy-mm-dd).
     * 
     * @param min ISO 8601 date-string "from"
     * @param max ISO 8601 date-string "until"
     * @return List of fees
     */
    public List<Fee> findByDateRange(String min, String max) {
        return repo.findByDateRange(min, max);
    }

    /**
     * Get all fees with a specific status.
     * 
     * @param status String of status
     * @return List of fees
     */
    public List<Fee> findByStatus(String status) {
        return repo.findByStatus(status);
    }

    /**
     * Get all fees with a specific amount.
     * 
     * @param amount Integer of amount
     * @return List of fees
     */
    public List<Fee> findByAmount(Integer amount) {
        return repo.findByAmount(amount);
    }
}
