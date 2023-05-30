package edu.hm.cs.swe2.memberfees.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import edu.hm.cs.swe2.memberfees.services.ReadFeeService;

/**
 * Integration tests for the controller that is responsible for the reading of
 * fees. It uses WebMvcTest to ensure that tests can run even without a
 * connected database.
 * 
 * @author Jonas Buse
 */
@WebMvcTest(ReadFeeController.class)
class ReadFeeControllerTest {

    @MockBean
    private ReadFeeService feeService;

    @MockBean
    private DataValidator validator;

    @Autowired
    private MockMvc mockMvc;

    private static List<Fee> feeList, emptyList; // NOPMD
    private static Fee fee;
    private static final String REST_PATH = "/api/v1/fees/";

    /**
     * Setup to run before the tests. Implemented mainly to avoid clutter.
     */
    @BeforeAll
    static void setup() {
        feeList = Arrays.asList(new Fee[] { new Fee("2020-02-02", 10, "pending", 2) });
        fee = new Fee("2020-02-02", 10, "pending", 2);
        emptyList = Arrays.asList(new Fee[] {});
    }

    /**
     * Tests the endpoint which returns all fees.
     */
    @Test
    void testGetAllFees() throws Exception {
        when(feeService.findAll()).thenReturn(feeList);

        mockMvc.perform(get(REST_PATH)).andExpect(status().isOk()).andExpect(jsonPath("$[0].date").value("2020-02-02"))
                .andExpect(jsonPath("$[0].amount").value(10)).andExpect(jsonPath("$[0].status").value("pending"))
                .andExpect(jsonPath("$[0].memberId").value(2));
    }

    /**
     * Tests the endpoint which returns a specific fee.
     */
    @Test
    void testGetFeeById() throws Exception {
        when(feeService.findById(any())).thenReturn(fee);
        when(validator.checkInt(any(), any())).thenReturn(true);

        mockMvc.perform(get(REST_PATH + "{id}", 2)).andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value("2020-02-02")).andExpect(jsonPath("$.amount").value(10))
                .andExpect(jsonPath("$.status").value("pending")).andExpect(jsonPath("$.memberId").value(2));
    }

    /**
     * Tests the endpoint which returns all fees from a specific member.
     */
    @Test
    void testFindByMemberId() throws Exception {
        when(feeService.findByMemberId(any())).thenReturn(feeList);
        when(validator.checkInt(any(), any())).thenReturn(true);

        mockMvc.perform(get(REST_PATH + "member/{id}", 2)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].date").value("2020-02-02")).andExpect(jsonPath("$[0].amount").value(10))
                .andExpect(jsonPath("$[0].status").value("pending")).andExpect(jsonPath("$[0].memberId").value(2));
    }

    /**
     * Tests the endpoint which returns all fees from a specific date.
     */
    @Test
    void testFindByDate() throws Exception {
        when(feeService.findByDate(any())).thenReturn(feeList);
        when(validator.checkDate(any())).thenReturn(true);

        mockMvc.perform(get(REST_PATH + "date/{date}", "2020-02-02")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].date").value("2020-02-02")).andExpect(jsonPath("$[0].amount").value(10))
                .andExpect(jsonPath("$[0].status").value("pending")).andExpect(jsonPath("$[0].memberId").value(2));
    }

    /**
     * Tests the endpoint which returns fees from given dates.
     */
    @Test
    void testFindByDateRange() throws Exception {
        when(feeService.findByDateRange(any(), any())).thenReturn(feeList);
        when(validator.checkDate(any())).thenReturn(true);

        mockMvc.perform(get(REST_PATH + "date/?from=2019-01-01&until=2022-01-01")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].date").value("2020-02-02")).andExpect(jsonPath("$[0].amount").value(10))
                .andExpect(jsonPath("$[0].status").value("pending")).andExpect(jsonPath("$[0].memberId").value(2));

        mockMvc.perform(get(REST_PATH + "/date/?from=2019-01-01")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].date").value("2020-02-02")).andExpect(jsonPath("$[0].amount").value(10))
                .andExpect(jsonPath("$[0].status").value("pending")).andExpect(jsonPath("$[0].memberId").value(2));
    }

    /**
     * Tests the endpoint which returns all fees with a specific status.
     */
    @Test
    void testFindByStatus() throws Exception {
        when(feeService.findByStatus(any())).thenReturn(feeList);
        when(validator.checkStatus(any())).thenReturn(true);

        mockMvc.perform(get(REST_PATH + "status/{status}", "pending")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].date").value("2020-02-02")).andExpect(jsonPath("$[0].amount").value(10))
                .andExpect(jsonPath("$[0].status").value("pending")).andExpect(jsonPath("$[0].memberId").value(2));
    }

    /**
     * Tests the endpoint which returns all fees with a specific amount.
     */
    @Test
    void testFindByAmount() throws Exception {
        when(feeService.findByAmount(any())).thenReturn(feeList);
        when(validator.checkInt(any(), any())).thenReturn(true);

        mockMvc.perform(get(REST_PATH + "amount/{amount}", 10)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].date").value("2020-02-02")).andExpect(jsonPath("$[0].amount").value(10))
                .andExpect(jsonPath("$[0].status").value("pending")).andExpect(jsonPath("$[0].memberId").value(2));
    }

    /**
     * Tests all endpoints in regard of the throwing of an 404 exception if the
     * returned List would be 0 in length.
     */
    @Test
    void testNotFoundException() throws Exception {
        when(feeService.findAll()).thenReturn(emptyList);
        when(feeService.findByMemberId(any())).thenReturn(emptyList);
        when(feeService.findByDate(any())).thenReturn(emptyList);
        when(feeService.findByDateRange(any(), any())).thenReturn(emptyList);
        when(feeService.findByStatus(any())).thenReturn(emptyList);
        when(feeService.findByAmount(any())).thenReturn(emptyList);

        mockMvc.perform(get(REST_PATH)).andExpect(status().isNotFound());
        mockMvc.perform(get(REST_PATH + "member/{id}", 2)).andExpect(status().isNotFound());
        mockMvc.perform(get(REST_PATH + "date/{date}", "2020-02-02")).andExpect(status().isNotFound());
        mockMvc.perform(get(REST_PATH + "date/?from=2019-01-01&until=2022-01-01")).andExpect(status().isNotFound());
        mockMvc.perform(get(REST_PATH + "date/?from=2019-01-01")).andExpect(status().isNotFound());
        mockMvc.perform(get(REST_PATH + "status/{status}", "pending")).andExpect(status().isNotFound());
        mockMvc.perform(get(REST_PATH + "amount/{amount}", 10)).andExpect(status().isNotFound());

    }

}
