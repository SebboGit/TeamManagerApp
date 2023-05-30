package edu.hm.cs.swe2.memberfees.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import edu.hm.cs.swe2.memberfees.models.Fee;
import edu.hm.cs.swe2.memberfees.services.ChangeFeeService;

/**
 * Test class for the changing of existing fees functionality.
 * 
 * @author Jonas Buse
 */
@WebMvcTest(ChangeFeeController.class)
class ChangeFeeControllerTest {

    @MockBean
    private ChangeFeeService feeService;

    @MockBean
    private DataValidator validator;

    @Autowired
    private MockMvc mockMvc;

    // ObjectMapper to convert objects to JSON
    ObjectMapper mapper = new ObjectMapper();

    private static final String REST_PATH = "/api/v1/fees/";

    /**
     * Testing the replacement endpoint.
     */
    @Test
    void testReplaceFee() throws Exception {
        Fee newFee = new Fee("1999-09-09", 50, "pending", 50);

        when(feeService.changeAllById(any(), any())).thenReturn(new Fee());
        when(validator.runAll(any())).thenReturn(true);

        mockMvc.perform(
                put(REST_PATH + "2").content(mapper.writeValueAsString(newFee)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Testing the changing endpoint.
     */
    @Test
    void testChangeFee() throws Exception {
        when(feeService.changeAllById(any(), any())).thenReturn(new Fee());
        when(validator.runAll(any())).thenReturn(true);

        mockMvc.perform(patch(REST_PATH + "2" + "?amount=50&date=1999-09-09&memberId=25&status=pending"))
                .andExpect(status().isOk());
        mockMvc.perform(patch(REST_PATH + "2" + "?amount=50&date=1999-09-09&memberId=25")).andExpect(status().isOk());
        mockMvc.perform(patch(REST_PATH + "2" + "?amount=50&date=1999-09-09")).andExpect(status().isOk());
        mockMvc.perform(patch(REST_PATH + "2" + "?amount=50")).andExpect(status().isOk());

        mockMvc.perform(patch(REST_PATH + "2" + "?")).andExpect(status().isBadRequest());
        mockMvc.perform(patch(REST_PATH + "2")).andExpect(status().isBadRequest());

    }

}