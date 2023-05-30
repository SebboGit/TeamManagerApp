package edu.hm.cs.swe2.eventPictures.controllers;

import edu.hm.cs.swe2.eventPictures.database.EventPictureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This is the test class for the DeleteEventPictureController.
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
@WebMvcTest(DeleteEventPictureController.class)
public class DeleteEventPictureControllerTest {

    @MockBean
    EventPictureService eventPictureService;

    @MockBean
    DataValidation dataValidation;

    @Autowired
    MockMvc mockMvc;

    /**
     * This test checks if the DELETE-Method with a given ID works correct.
     *
     * @throws Exception at the perform() method
     */
    @Test
    void testDeleteEventPicture() throws Exception {
        doNothing().when(eventPictureService).deleteEventPicture(anyInt());
        when(dataValidation.validateId(anyInt())).thenReturn(true);
        when(eventPictureService.checkEventPictureId(anyInt())).thenReturn(true);

        mockMvc.perform(delete("/api/v1/EventPictures/delete/{id}", 1)).andExpect(status().isOk());
    }

    /**
     * This test checks if the Exception is thrown when a Event Picture is not present in the database.
     *
     * @throws Exception at the perform() method
     */
    @Test
    void testNoSuchElementException() throws Exception {
        doThrow(NoSuchElementException.class).when(eventPictureService).deleteEventPicture(anyInt());
        when(dataValidation.validateId(anyInt())).thenReturn(true);

        mockMvc.perform(delete("/api/v1/EventPictures/delete/{id}", 1)).andExpect(status().isNotFound());
    }
}
