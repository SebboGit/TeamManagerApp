package edu.hm.cs.swe2.memberfees.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.hm.cs.swe2.memberfees.models.Fee;

/**
 * Repository extending the JPARepository to deliver basic Database interaction
 * as well as pagination and sorting.
 * 
 * @author Jonas Buse
 */
@Repository
public interface FeeRepository extends JpaRepository<Fee, Integer> {

    /**
     * A query that returns all fees from a given member id.
     * 
     * @param id member id
     * @return List of fees
     */
    List<Fee> findByMemberId(Integer id);

    /**
     * A query that returns all fees with a given date.
     * 
     * @param date ISO 8601 date-string
     * @return List of fees
     */
    List<Fee> findByDate(String date);

    /**
     * A query that returns all fees between and on two given dates.
     * 
     * @param min ISO 8601 date-string "from"
     * @param max ISO 8601 date-string "until"
     * @return List of fees
     */
    @Query("select f from Fee f where f.date >= ?1 and f.date <= ?2")
    List<Fee> findByDateRange(String min, String max);

    /**
     * A query that returns all fees of a given status.
     * 
     * @param status status
     * @return List of fees
     */
    List<Fee> findByStatus(String status);

    /**
     * A query that returns all fees with a given amount.
     * 
     * @param amount amount
     * @return List of fees
     */
    List<Fee> findByAmount(Integer amount);

    /**
     * A query that changes the date of a specific fee.
     * 
     * @param id      - Integer
     * @param newDate - the new date in ISO 8601 format
     */
    @Modifying
    @Transactional
    @Query("UPDATE Fee f SET f.date = ?2 WHERE f.id = ?1")
    void changeDateById(Integer id, String newDate);

    /**
     * A query that changes a fees amount.
     * 
     * @param id        - Integer
     * @param newAmount - the new amount as Integer
     */
    @Modifying
    @Transactional
    @Query("UPDATE Fee f SET f.amount = ?2 WHERE f.id = ?1")
    void changeAmountById(Integer id, Integer newAmount);

    /**
     * A query that changes a fees status.
     * 
     * @param id        - Integer
     * @param newStatus - the new status, either "pending" or "confirmed"
     */
    @Modifying
    @Transactional
    @Query("UPDATE Fee f SET f.status = ?2 WHERE f.id = ?1")
    void changeStatusById(Integer id, String newStatus);

    /**
     * A query that changes a fees associated member id.
     * 
     * @param id          - Integer
     * @param newMemberId - the new memberId as Integer
     */
    @Modifying
    @Transactional
    @Query("UPDATE Fee f SET f.memberId = ?2 WHERE f.id = ?1")
    void changeMemberIdById(Integer id, Integer newMemberId);

}