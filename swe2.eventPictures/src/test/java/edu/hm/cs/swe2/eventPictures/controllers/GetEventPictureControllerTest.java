package edu.hm.cs.swe2.eventPictures.controllers;

import edu.hm.cs.swe2.eventPictures.database.EventPictureService;
import edu.hm.cs.swe2.eventPictures.models.EventPicture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This is the test class for the GetEventPictureController.
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
@WebMvcTest(GetEventPictureController.class)
public class GetEventPictureControllerTest {

    @MockBean
    EventPictureService eventPictureService;

    @MockBean
    DataValidation dataValidation;

    @Autowired
    MockMvc mockMvc;

    private static List<EventPicture> eventPictureList;
    private static List<EventPicture> emptyEventPictureList;
    private static EventPicture eventPicture;
    private static EventPicture emptyEventPicture;


    /**
     * This method sets up the Event Pictures for the tests.
     */
    @BeforeAll
    static void setup() {
        eventPictureList = Arrays.asList(new EventPicture[]{new EventPicture(1, "test_title1", "test_description1"),
                new EventPicture(2, "test_title2", "test_description2"),
                new EventPicture(3, "test_title1", "test_description2")});
        emptyEventPictureList = Arrays.asList(new EventPicture[]{});
        eventPicture = new EventPicture(4, "test_title4", "test_description4");
        emptyEventPicture = new EventPicture();
    }

    /**
     * This test checks if the GET-Method for all Event Pictures works correct.
     *
     * @throws Exception at the perform() method
     */
    @Test
    void testGetAllEventPictures() throws Exception {
        when(eventPictureService.getAllEventPicture()).thenReturn(eventPictureList);

        mockMvc.perform(get("/api/v1/EventPictures/getAll")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$[0].id").value(1)).
                andExpect(jsonPath("$[0].title").value("test_title1")).
                andExpect(jsonPath("$[0].description").value("test_description1")).
                andExpect(jsonPath("$[1].id").value(2)).
                andExpect(jsonPath("$[1].title").value("test_title2")).
                andExpect(jsonPath("$[1].description").value("test_description2")).
                andExpect(jsonPath("$[2].id").value(3)).
                andExpect(jsonPath("$[2].title").value("test_title1")).
                andExpect(jsonPath("$[2].description").value("test_description2"));
    }

    /**
     * This test checks if the GET-Method with a given ID works correct.
     *
     * @throws Exception at the perform() method
     */
    @Test
    void testGetEventPicture() throws Exception {
        when(eventPictureService.getEventPicture(anyInt())).thenReturn(eventPicture);
        when(dataValidation.validateId(anyInt())).thenReturn(true);
        when(eventPictureService.checkEventPictureId(anyInt())).thenReturn(true);

        mockMvc.perform(get("/api/v1/EventPictures/get/{id}", 4)).andExpect(status().isOk()).
                andExpect(jsonPath("id").value(4)).
                andExpect(jsonPath("title").value("test_title4")).
                andExpect(jsonPath("description").value("test_description4"));
    }

    /**
     * This test checks if the GET-Method with a given event ID works correct.
     *
     * @throws Exception at the perform() method
     */
    @Test
    void testGetEventPictureByEvent() throws Exception {
        eventPictureList.get(0).setEvent(10);
        eventPictureList.get(1).setEvent(10);
        when(eventPictureService.getByEventId(anyInt())).thenReturn(eventPictureList);
        when(dataValidation.validateId(anyInt())).thenReturn(true);
        when(eventPictureService.checkEventPictureEventId(anyInt())).thenReturn(true);

        mockMvc.perform(get("/api/v1/EventPictures/getByEvent/{eventId}", 10)).andExpect(status().isOk()).
                andExpect(jsonPath("$[0].id").value(1)).
                andExpect(jsonPath("$[0].title").value("test_title1")).
                andExpect(jsonPath("$[0].description").value("test_description1")).
                andExpect(jsonPath("$[0].event").value(10)).
                andExpect(jsonPath("$[1].id").value(2)).
                andExpect(jsonPath("$[1].title").value("test_title2")).
                andExpect(jsonPath("$[1].description").value("test_description2")).
                andExpect(jsonPath("$[1].event").value(10));
    }

    /**
     * This test checks if the GET-Method with a given title works correct.
     *
     * @throws Exception at the perform() method
     */
    @Test
    void testGetEventPictureByTitle() throws Exception {
        when(eventPictureService.getByTitle(any())).thenReturn(eventPictureList);
        when(eventPictureService.checkEventPictureTitle(any())).thenReturn(true);

        mockMvc.perform(get("/api/v1/EventPictures/getByTitle/{title}", "test_title1")).andExpect(status().isOk()).
                andExpect(jsonPath("$[0].id").value(1)).
                andExpect(jsonPath("$[0].title").value("test_title1")).
                andExpect(jsonPath("$[0].description").value("test_description1")).
                andExpect(jsonPath("$[2].id").value(3)).
                andExpect(jsonPath("$[2].title").value("test_title1")).
                andExpect(jsonPath("$[2].description").value("test_description2"));
    }

    /**
     * This test checks if the GET-Method with a given description works correct.
     *
     * @throws Exception at the perform() method
     */
    @Test
    void testGetEventPictureByDescription() throws Exception {
        when(eventPictureService.getByDescription(any())).thenReturn(eventPictureList);
        when(eventPictureService.checkEventPictureDescription(any())).thenReturn(true);

        mockMvc.perform(get("/api/v1/EventPictures/getByDescription/{description}", "test_description2")).andExpect(status().isOk()).
                andExpect(jsonPath("$[1].id").value(2)).
                andExpect(jsonPath("$[1].title").value("test_title2")).
                andExpect(jsonPath("$[1].description").value("test_description2")).
                andExpect(jsonPath("$[2].id").value(3)).
                andExpect(jsonPath("$[2].title").value("test_title1")).
                andExpect(jsonPath("$[2].description").value("test_description2"));
    }

    /**
     * This test checks if the GET-Method with a given rating works correct.
     *
     * @throws Exception at the perform() method
     */
    @Test
    void testGetEventPictureByRating() throws Exception {
        eventPictureList.get(0).setRating(5);
        eventPictureList.get(1).setRating(5);
        when(eventPictureService.getByRating(anyInt())).thenReturn(eventPictureList);
        when(dataValidation.validateId(anyInt())).thenReturn(true);
        when(eventPictureService.checkEventPictureRating(anyInt())).thenReturn(true);

        mockMvc.perform(get("/api/v1/EventPictures/getByRating/{rating}", 5)).andExpect(status().isOk()).
                andExpect(jsonPath("$[0].id").value(1)).
                andExpect(jsonPath("$[0].title").value("test_title1")).
                andExpect(jsonPath("$[0].description").value("test_description1")).
                andExpect(jsonPath("$[0].rating").value(5)).
                andExpect(jsonPath("$[1].id").value(2)).
                andExpect(jsonPath("$[1].title").value("test_title2")).
                andExpect(jsonPath("$[1].description").value("test_description2")).
                andExpect(jsonPath("$[1].rating").value(5));
    }

    /**
     * This test checks if the Exception is thrown when a Event Picture is not present in the database.
     *
     * @throws Exception at the perform() method
     */
    @Test
    void testNoSuchElementException() throws Exception {
        when(eventPictureService.getAllEventPicture()).thenReturn(emptyEventPictureList);
        when(eventPictureService.getEventPicture(anyInt())).thenReturn(emptyEventPicture);
        when(eventPictureService.getByEventId(anyInt())).thenReturn(emptyEventPictureList);
        when(eventPictureService.getByTitle(any())).thenReturn(emptyEventPictureList);
        when(eventPictureService.getByDescription(any())).thenReturn(emptyEventPictureList);
        when(eventPictureService.getByRating(anyInt())).thenReturn(emptyEventPictureList);
        when(dataValidation.validateId(anyInt())).thenReturn(true);
        when(dataValidation.validateId(anyInt())).thenReturn(true);

        mockMvc.perform(get("/api/v1/EventPictures/getAll")).andExpect(status().isNotFound());
        mockMvc.perform(get("/api/v1/EventPictures/get/{id}", 1)).andExpect(status().isNotFound());
        mockMvc.perform(get("/api/v1/EventPictures/getByEvent/{eventId}", 2)).andExpect(status().isNotFound());
        mockMvc.perform(get("/api/v1/EventPictures/getByTitle/{title}", "test_exception")).andExpect(status().isNotFound());
        mockMvc.perform(get("/api/v1/EventPictures/getByDescription/{description}", "test_exception")).andExpect(status().isNotFound());
        mockMvc.perform(get("/api/v1/EventPictures/getByRating/{rating}", 3)).andExpect(status().isNotFound());
    }
}
