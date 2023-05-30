package edu.hm.cs.swe2.memberfees.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hm.cs.swe2.memberfees.database.FeeRepository;
import edu.hm.cs.swe2.memberfees.models.Fee;

/**
 * Service class to implement 'create' Repository functions.
 * 
 * @author Jonas Buse
 */
@Service
public class CreateFeeService {

    @Autowired
    private FeeRepository repo;

    /**
     * Save a list of fees. This is more effective than saving each fee
     * sequencially.
     * 
     * @param fees A list of fees wich are to be added.
     * @return the added fees as a list.
     */
    public List<Fee> saveFees(List<Fee> fees) {
        return repo.saveAll(fees);
    }

}
