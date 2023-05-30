package edu.hm.cs.swe2.memberfees.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hm.cs.swe2.memberfees.database.FeeRepository;

/**
 * Service class to implement 'delete' Repository functions.
 * 
 * @author Jonas Buse
 */
@Service
public class DeleteFeeService {

    @Autowired
    private FeeRepository repo;

    /**
     * Delete a specific fee.
     */
    public void deleteFee(Integer id) {
        this.repo.deleteById(id);
    }
}
