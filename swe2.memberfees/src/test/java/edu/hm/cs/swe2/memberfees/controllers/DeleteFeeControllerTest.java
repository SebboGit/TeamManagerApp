package edu.hm.cs.swe2.memberfees.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.web.servlet.MockMvc;

import edu.hm.cs.swe2.memberfees.services.DeleteFeeService;

/**
 * Integration tests for the controller that is responsible for the deletion of
 * fees. It uses WebMvcTest to ensure that tests can run even without a
 * connected database.
 * 
 * @author Jonas Buse
 */
@WebMvcTest(DeleteFeeController.class)
class DeleteFeeControllerTest {

    @MockBean
    private DeleteFeeService service;

    @MockBean
    private DataValidator validator;

    @Autowired
    private MockMvc mockMvc;

    private static final String REST_PATH = "/api/v1/fees/";

    /**
     * Tests the deletion process.
     */
    @Test
    void testDeleteFees() throws Exception {
        doNothing().when(service).deleteFee(any());
        when(validator.checkInt(any(), any())).thenReturn(true);

        mockMvc.perform(delete(REST_PATH + "{id}", 2)).andExpect(status().isOk());
    }

    /**
     * Tests the exception-handling.
     */
    @Test
    void testDeleteFeesException() throws Exception {
        doThrow(EmptyResultDataAccessException.class).when(service).deleteFee(any());
        when(validator.checkInt(any(), any())).thenReturn(true);

        mockMvc.perform(delete(REST_PATH + "{id}", 2)).andExpect(status().isNotFound());
    }
}
