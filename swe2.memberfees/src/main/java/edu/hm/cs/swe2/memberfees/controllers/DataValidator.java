package edu.hm.cs.swe2.memberfees.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import edu.hm.cs.swe2.memberfees.models.Fee;

/**
 * Helper class to verify data according to specific metrics. Throws Exceptions
 * with relevant information if not.
 * 
 * @author Jonas Buse
 */
@Component
public class DataValidator {

    /**
     * Checks the format of a date String to be of ISO 8601 standard. (YYYY-MM-DD)
     * 
     * @param date ISO 8601 date-string
     * @return boolean
     * @throws IllegalArgumentException if the String passed was not of the right
     *                                  format or an illegal date
     * @throws NullPointerException     if no date was given
     */
    public boolean checkDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(String.format("'%s' is not a date!", date));
        } catch (NullPointerException e) {
            throw new NullPointerException("No 'date' was given!");
        }

        return true;
    }

    /**
     * Checks to see if a number is postive.
     * 
     * @param number  Integer
     * @param useCase String of usecase, which will be printed in case of an
     *                exception
     * @return boolean
     * @throws IllegalArgumentException if the number is not posivite
     * @throws NullPointerException     if no number was given
     */
    public boolean checkInt(Integer number, String useCase) {
        try {
            int min = 1;
            if (number < min)
                throw new IllegalArgumentException(String.format("'%d' is not a positive value!", number));

            return true;
        } catch (NullPointerException e) {
            throw new NullPointerException(String.format("No '%s' was given!", useCase));
        }
    }

    /**
     * Checks if the status is one of the predefined Strings ["pending",
     * "confirmed"].
     * 
     * @param status String of status
     * @return boolean
     * @throws IllegalArgumentException if the status is not valid
     */
    public boolean checkStatus(String status) {
        List<String> valid = Arrays.asList("pending", "confirmed");
        if (!valid.contains(status))
            throw new IllegalArgumentException(String.format("'%s' is not a status!", status));

        return true;
    }

    /**
     * Runs all checks with a given fee.
     * 
     * @param fee fee to be checked
     * @return boolean
     * @throws IllegalArgumentException according to the individual tests
     * @throws NullPointerException     according to the indivdual tests
     */
    public boolean runAll(Fee fee) {
        String errorMessage = String.format("No items were saved! \nProblematic item: [%s]: \n", fee.toString());

        try {
            this.checkDate(fee.getDate());
            this.checkInt(fee.getAmount(), "amount");
            this.checkInt(fee.getMemberId(), "memberId");
            this.checkStatus(fee.getStatus());

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(errorMessage + e.getMessage());
        } catch (NullPointerException e) {
            throw new NullPointerException(errorMessage + e.getMessage());
        }

        return true;
    }
}
