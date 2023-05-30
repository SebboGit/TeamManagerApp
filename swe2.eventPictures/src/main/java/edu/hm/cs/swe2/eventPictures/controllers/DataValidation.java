package edu.hm.cs.swe2.eventPictures.controllers;

import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindingResult;

/**
 * This class validates the user input and throws exceptions, if the values aren't correct
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
@Component
public class DataValidation {

    /**
     * This method checks if the information input, which should be stored with the associated image, includes a type mismatch.
     * It throws the IllegalArgumentException.
     *
     * @param result contains the occurred Errors
     */
    public boolean bindingFailure(BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException("The binding failure was caused by a type mismatch in the arguments!");
        }
        return true;
    }

    /**
     * This method checks if the rating number was between 1 and 5.
     * If not, then the TransactionSystemException will be thrown.
     *
     * @param rating rating number from the Client side
     */
    public boolean falseRatingNumber(int rating) {
        if (rating < 1 || rating > 5) {
            throw new TransactionSystemException("The rating number must be between 1 and 5!");
        }
        return true;
    }

    /**
     * validates if the given ID is illegal (e.g. -23, -1, 0)
     * and throws an IllegalArgumentException
     *
     * @param id ID of an Event Picture from the Client side
     */
    public boolean validateId(int id) {
        if (id < 1) {
            throw new IllegalArgumentException(String.format("The given ID value '%d' is not a valid input. The value should be at least 1!", id));
        }
        return true;
    }
}
