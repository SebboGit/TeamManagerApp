package edu.hm.cs.swe2.memberfees.controllers;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import edu.hm.cs.swe2.memberfees.models.Fee;
import edu.hm.cs.swe2.memberfees.services.CalculateFeeService;

/**
 * Test class for the calculation of new fees functionality.
 * 
 * @author Jonas Buse
 */
@WebMvcTest(CalculateFeeController.class)
class CalculateFeeControllerTest {

    @MockBean
    private CalculateFeeService feeService;

    @MockBean
    private DataValidator validator;

    @Autowired
    private MockMvc mockMvc;

    private static List<Fee> feeList;
    private static final String REST_PATH = "/api/v1/fees/calculate/";

    /**
     * Setup to run before the tests. Implemented mainly to avoid clutter.
     */
    @BeforeAll
    static void setup() {
        feeList = Arrays.asList(new Fee[] { new Fee("2020-02-02", 10, "pending", 2) });
    }

    /**
     * Test the default calculate fees method using a GET request and a given year.
     */
    @Test
    void testCalculateFees() throws Exception {
        when(feeService.calculateFees(anyInt(), anyBoolean())).thenReturn(feeList);
        when(validator.checkInt(anyInt(), anyString())).thenReturn(true);

        mockMvc.perform(get(REST_PATH + "2021")).andExpect(status().isOk());
    }

    /**
     * Test the calculate fees method using a GET request and no year.
     */
    @Test
    void testCalculateFeesThisYear() throws Exception {
        when(feeService.calculateFees(anyInt(), anyBoolean())).thenReturn(feeList);

        mockMvc.perform(get(REST_PATH)).andExpect(status().isOk());
    }

    /**
     * Test the calculate fees method using a POST request and a given year.
     */
    @Test
    void testCalculateFeesAndSave() throws Exception {
        when(feeService.calculateFees(anyInt(), anyBoolean())).thenReturn(feeList);
        when(validator.checkInt(anyInt(), anyString())).thenReturn(true);

        mockMvc.perform(post(REST_PATH + "2021")).andExpect(status().isCreated());
    }

}
