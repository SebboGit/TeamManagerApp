package edu.hm.cs.swe2.memberfees.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hm.cs.swe2.memberfees.database.FeeRepository;
import edu.hm.cs.swe2.memberfees.models.Fee;

/**
 * Service that handles all operations regarding the changing of fees.
 * 
 * @author Jonas Buse
 */
@Service
public class ChangeFeeService {

    @Autowired
    private FeeRepository repo;

    /**
     * Changing a fees Date.
     * 
     * @param id      - Integer
     * @param newDate - the new date in ISO 8601 format
     * @return the modified fee
     */
    public Fee changeDateById(Integer id, String newDate) {
        this.repo.changeDateById(id, newDate);
        return this.repo.findById(id).get();
    }

    /**
     * Changing a fees amount.
     * 
     * @param id        - Integer
     * @param newAmount - the new amount as Integer
     * @return the modified fee
     */
    public Fee changeAmountById(Integer id, Integer newAmount) {
        this.repo.changeAmountById(id, newAmount);
        return this.repo.findById(id).get();
    }

    /**
     * Changing a fees associated member id.
     * 
     * @param id          - Integer
     * @param newMemberId - the new memberId as Integer
     * @return the modified fee
     */
    public Fee changeMemberIdById(Integer id, Integer newMemberId) {
        this.repo.changeMemberIdById(id, newMemberId);
        return this.repo.findById(id).get();
    }

    /**
     * Changing a fees status.
     * 
     * @param id        - Integer
     * @param newStatus - the new status, either "pending" or "confirmed"
     * @return the modified fee
     */
    public Fee changeStatusById(Integer id, String newStatus) {
        this.repo.changeStatusById(id, newStatus);
        return this.repo.findById(id).get();
    }

    /**
     * Changes all attributes which are not null from a given new Fee.
     * 
     * @param id  - Integer
     * @param fee - the new fee
     * @return the modified fee
     */
    public Fee changeAllById(Integer id, Fee fee) {
        if (fee.getStatus() != null)
            this.repo.changeStatusById(id, fee.getStatus());

        if (fee.getAmount() != null)
            this.repo.changeAmountById(id, fee.getAmount());

        if (fee.getDate() != null)
            this.repo.changeDateById(id, fee.getDate());

        if (fee.getMemberId() != null)
            this.repo.changeMemberIdById(id, fee.getMemberId());

        return this.repo.findById(id).get();
    }

}
