package edu.hm.cs.swe2.memberfees.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import edu.hm.cs.swe2.memberfees.models.Fee;

/**
 * Unit tests for the data validation.
 * 
 * @author Jonas Buse
 */
class DataValidationTest {

    private final DataValidator validator = new DataValidator();

    /**
     * Tests the functionality of date checking by passing a few correct and a few
     * wrong Strings.
     */
    @Test
    void testCheckDate() {
        // Testing the the inteded/normal use
        assertThat(validator.checkDate("2020-01-01")).isTrue();
        assertThat(validator.checkDate("2024-02-02")).isTrue();

        // Testing the handling of the IllegalArgumentException
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            validator.checkDate("2020-13-02");
        }).withMessageContaining("'%s' is not a date!", "2020-13-02");

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            validator.checkDate("text");
        }).withMessageContaining("'%s' is not a date!", "text");

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            validator.checkDate("2020.13.02");
        }).withMessageContaining("'%s' is not a date!", "2020.13.02");

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            validator.checkDate("2020-1-2");
        }).withMessageContaining("'%s' is not a date!", "2020-1-2");

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            validator.checkDate("");
        }).withMessageContaining("'%s' is not a date!", "");

        // Testing the handling of the NullPointerException
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
            validator.checkDate(null);
        }).withMessageContaining("No 'date' was given!");
    }

    /**
     * Tests the functionality of number checking by passing a few correct and a few
     * wrong numbers.
     */
    @Test
    void testCheckInt() {
        // Testing the the inteded/normal use
        assertThat(validator.checkInt(2, "test")).isTrue();
        assertThat(validator.checkInt(1000, "test")).isTrue();
        assertThat(validator.checkInt(1, "test")).isTrue();

        // Testing the handling of the IllegalArgumentException
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            validator.checkInt(0, "test");
        }).withMessageContaining("'%d' is not a positive value!", 0);

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            validator.checkInt(-5, "test");
        }).withMessageContaining("'%d' is not a positive value!", -5);

        // Testing the handling of the NullPointerException
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
            validator.checkInt(null, "test");
        }).withMessageContaining("No '%s' was given!", "test");
    }

    /**
     * Tests the functionality of status checking by passing a few correct and a few
     * wrong strings.
     */
    @Test
    void testCheckStatus() {
        // Testing the the inteded/normal use
        assertThat(validator.checkStatus("confirmed")).isTrue();
        assertThat(validator.checkStatus("pending")).isTrue();

        // Testing the handling of the IllegalArgumentException
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            validator.checkStatus("test");
        }).withMessageContaining("'%s' is not a status!", "test");

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            validator.checkStatus("12");
        }).withMessageContaining("'%s' is not a status!", "12");

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            validator.checkStatus("");
        }).withMessageContaining("'%s' is not a status!", "");

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            validator.checkStatus(null);
        }).withMessageContaining("'%s' is not a status!", "null");
    }

    /**
     * Testing the runAll method, by mocking the other 'check' methods away.
     * 
     * One Test to see if the normal sequence workds, one with a NullPointer- and
     * one with an IllegalArgumentsException.
     */
    @Test
    void testRunAll() {
        Fee testFee = new Fee("2020-02-02", 50, "pending", 5);
        // Creating a spy to 'mock' away the previously tested methods
        DataValidator spyValidator = Mockito.spy(validator);

        // Testing the intended/normal use
        Mockito.doReturn(true).when(spyValidator).checkDate(any());
        Mockito.doReturn(true).when(spyValidator).checkStatus(any());
        Mockito.doReturn(true).when(spyValidator).checkInt(any(), any());

        assertThat(spyValidator.runAll(testFee)).isTrue();

        // Testing a NullPointerException from any of the 'check' methods
        Mockito.doThrow(NullPointerException.class).when(spyValidator).checkDate(any());

        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
            spyValidator.runAll(testFee);
        }).withMessageContaining("No items were saved! \nProblematic item: [%s]: \n", testFee.toString());

        // Testing an IllegalArgumentException from any of the 'check' methods
        Mockito.doThrow(IllegalArgumentException.class).when(spyValidator).checkDate(any());

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            spyValidator.runAll(testFee);
        }).withMessageContaining("No items were saved! \nProblematic item: [%s]: \n", testFee.toString());
    }
}
