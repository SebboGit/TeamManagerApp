package edu.hm.cs.swe2.memberfees.controllers;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import edu.hm.cs.swe2.memberfees.models.Fee;
import edu.hm.cs.swe2.memberfees.services.CreateFeeService;

/**
 * Integration tests for the controller that is responsible for the creation of
 * fees. It uses WebMvcTest to ensure that tests can run even without a
 * connected database.
 * 
 * @author Jonas Buse
 */
@WebMvcTest(CreateFeeController.class)
class CreateFeeControllerTest {

        @MockBean
        private CreateFeeService feeService;

        @MockBean
        private DataValidator validator;

        // ObjectMapper to convert objects to JSON
        ObjectMapper mapper = new ObjectMapper();

        @Autowired
        private MockMvc mockMvc;

        private static final String REST_PATH = "/api/v1/fees";

        /**
         * Test posts valid new fees and expects status 200 codes.
         */
        @Test
        void testCreateFeeStatus() throws Exception {
                List<Fee> feeListTwo = Arrays.asList(new Fee[] { new Fee("2021-02-02", 20, "pending", 5),
                                new Fee("2021-03-03", 50, "confirmed", 8) });
                List<Fee> feeListOne = Arrays.asList(new Fee[] { new Fee("2021-02-02", 20, "pending", 5) });

                when(feeService.saveFees(any())).then(returnsFirstArg());
                when(validator.runAll(any())).thenReturn(true);

                mockMvc.perform(post(REST_PATH).content(mapper.writeValueAsString(feeListTwo))
                                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

                mockMvc.perform(post(REST_PATH).content(mapper.writeValueAsString(feeListOne))
                                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
        }

        /**
         * The test posts an empty List and expects a 400 status code.
         */
        @Test
        void testCreateFeeException() throws Exception {
                mockMvc.perform(post(REST_PATH).content(mapper.writeValueAsString(new ArrayList<Fee>()))
                                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
        }

}
