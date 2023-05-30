package edu.hm.cs.swe2.eventPictures.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.TransactionSystemException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is the test class for the DataValidation.
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
public class DataValidationTest {

    DataValidation dataValidation = new DataValidation();

    /**
     * This test checks if different rating numbers are valid.
     */
    @Test
    void testFalseRatingNumber() {
        assertThrows(TransactionSystemException.class, () -> dataValidation.falseRatingNumber(-4));
        assertThrows(TransactionSystemException.class, () -> dataValidation.falseRatingNumber(0));
        assertThrows(TransactionSystemException.class, () -> dataValidation.falseRatingNumber(6));
        assertTrue(dataValidation.falseRatingNumber(2));
    }

    /**
     * This test checks if different IDs are valid.
     */
    @Test
    void testValidateId() {
        assertThrows(IllegalArgumentException.class, () -> dataValidation.validateId(-1));
        assertThrows(IllegalArgumentException.class, () -> dataValidation.validateId(0));
        assertTrue(dataValidation.validateId(3));
    }

}
