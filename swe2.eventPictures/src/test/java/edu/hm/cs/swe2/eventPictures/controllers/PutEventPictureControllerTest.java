package edu.hm.cs.swe2.eventPictures.controllers;

import edu.hm.cs.swe2.eventPictures.database.EventPictureService;
import edu.hm.cs.swe2.eventPictures.models.EventPicture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This is the test class for the PutEventPictureController.
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
@SpringBootTest
@AutoConfigureMockMvc
public class PutEventPictureControllerTest {

    @MockBean
    EventPictureService eventPictureService;

    @MockBean
    DataValidation dataValidation;

    @Autowired
    MockMvc mockMvc;

    /**
     * This test checks if the PUT-Method with a given ID works correct.
     *
     * @throws Exception at the perform() method
     */
    @Test
    void testUpdateEventPicture() throws Exception {
        when(eventPictureService.updateEventPicture(anyInt(), any(), any())).thenReturn(new EventPicture());
        when(dataValidation.validateId(anyInt())).thenReturn(true);
        when(dataValidation.bindingFailure(any())).thenReturn(true);
        when(dataValidation.falseRatingNumber(anyInt())).thenReturn(true);
        when(eventPictureService.checkEventPictureId(anyInt())).thenReturn(true);

        mockMvc.perform(put("/api/v1/EventPictures/update/{id}", 1)).andExpect(status().isOk());
    }

    /**
     * This test checks if the Exception is thrown when a Event Picture is not present in the database.
     *
     * @throws Exception at the perform() method
     */
    @Test
    void testNoSuchElementException() throws Exception {
        doThrow(NoSuchElementException.class).when(eventPictureService).updateEventPicture(anyInt(), any(), any());
        when(dataValidation.validateId(anyInt())).thenReturn(true);

        mockMvc.perform(put("/api/v1/EventPictures/update/{id}?title=testException", 1)).andExpect(status().isNotFound());
    }
}
