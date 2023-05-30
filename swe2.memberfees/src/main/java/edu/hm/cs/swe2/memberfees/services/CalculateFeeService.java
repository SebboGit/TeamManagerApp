package edu.hm.cs.swe2.memberfees.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hm.cs.swe2.memberfees.database.FeeRepository;
import edu.hm.cs.swe2.memberfees.models.Fee;
import edu.hm.cs.swe2.memberfees.models.MemberPlaceholder;

/**
 * Service that handles all operations regarding the calculation of fees.
 * 
 * @author Jonas Buse
 */
@Service
public class CalculateFeeService {

    @Autowired
    private FeeRepository repo;

    private static final MemberPlaceholder[] membersPH = { // NOPMD
            new MemberPlaceholder(1, "Jonas", "Buse", "2020-03-03", null, new int[] { 1, 2 }),
            new MemberPlaceholder(2, "Dude", "Guy", "2021-03-02", null, new int[] { 1 }),
            new MemberPlaceholder(3, "Neil", "Armstrong", "2021-01-01", "2021-03-20", new int[] { 3 }),
            new MemberPlaceholder(4, "John", "Doe", "2019-07-23", null, new int[] { 1, 2, 3 }),
            new MemberPlaceholder(5, "Jane", "Doe", "2019-10-23", "2020-02-02", new int[] {}),
            new MemberPlaceholder(6, "John", "Smith", "2021-07-15", null, new int[] { 2 }),
            new MemberPlaceholder(7, "Max", "Mustermann", "2021-01-03", "2021-01-04", new int[] {}),
            new MemberPlaceholder(8, "Some", "OldDude", "2002-07-15", null, new int[] {}) };

    /**
     * private function that retrieves and returns all members
     */
    MemberPlaceholder[] getAllMembers() {
        // TODO: Implement backend connection to memberadmin once available
        return membersPH; // NOPMD
    }

    /**
     * Calculate new fees for all members, based on team affiliation and date of
     * entry. This can be called in a non persistant or persistand manner.
     * 
     * @param year    - int year for which the fees are to be calculated
     * @param persist - boolean if results should be written to db
     * @return List of Fees
     */
    public List<Fee> calculateFees(int year, boolean persist) {
        List<Fee> newFees = new ArrayList<Fee>();

        for (MemberPlaceholder member : getAllMembers()) {
            LocalDate entryDate = LocalDate.parse(member.getEntryDate());
            LocalDate leavingDate = (member.getLeavingDate() == null) ? null : LocalDate.parse(member.getLeavingDate());
            String nowDate = LocalDate.now().toString();
            // if the member joined in the first half of the given year or earlier
            // and if the members has left, but in this year
            if (entryDate.isBefore(LocalDate.of(year, 7, 2))
                    && (leavingDate == null || leavingDate.isAfter(LocalDate.of(year, 1, 31)))) {
                int amount = calculateSingleFee(entryDate, leavingDate, member.getMemberships().length, year);

                if (amount > 0) {
                    newFees.add(new Fee(nowDate, amount, "pending", member.getId()));
                }
            }
        }

        if (persist) {
            return repo.saveAll(newFees);
        }

        return newFees;
    }

    /**
     * Private function that calculates an amount for a single new fee based on a
     * few parameters. If the member left in the first half of the year to be
     * calculated, no amount is returned. Only full months are calculated.
     * 
     * @param entryDate        - LocalDate of the date of entry
     * @param exitDate         - LocalDate of the date of exit
     * @param membershipsCount - int amount of teams the member is part of
     * @param year             - int the year to be calculated
     * @return an amount to be payed
     */
    int calculateSingleFee(LocalDate entryDate, LocalDate exitDate, int membershipsCount, int year) {
        if (entryDate.isAfter(LocalDate.of(year, 7, 2))) {
            return 0;
        }

        // 1 Team: 5, 2 Teams: 4, ... , 5 Teams: 1, 6 Teams: 1, ...
        int amountPerTeam = membershipsCount > 5 ? 5 : membershipsCount;
        amountPerTeam = amountPerTeam * -1 + 6;
        // if the member joined in the year to be calculated, change the starting point
        // to the point of entry
        LocalDate fromDate = (entryDate.getYear() == year) ? entryDate : LocalDate.of(year, 1, 1);
        // if the year to be calculated is the current year, take the current date as
        // endpoint, else take the 01.12.year
        LocalDate untilDate = (year == LocalDate.now().getYear()) ? LocalDate.now() : LocalDate.of(year, 12, 01);
        // if the member left in the year, take the exitDate as lower bound to calculate
        if (exitDate != null) {
            untilDate = (year == exitDate.getYear()) ? exitDate : untilDate;
        }

        int amount = 0;
        long months = fromDate.until(untilDate, ChronoUnit.MONTHS);
        amount += 10 * months;
        amount += amountPerTeam * membershipsCount * months;
        return amount;
    }

}
