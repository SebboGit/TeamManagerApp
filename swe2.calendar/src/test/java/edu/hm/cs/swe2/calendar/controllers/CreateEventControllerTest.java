package edu.hm.cs.swe2.calendar.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.cs.swe2.calendar.models.Event;
import edu.hm.cs.swe2.calendar.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Rollback
public class CreateEventControllerTest {

  @MockBean private EventService eventService;

  @Autowired private MockMvc mvc;

  ObjectMapper mapper = new ObjectMapper();

  @Test
  public void createEventTest() throws Exception {
    List<Event> events = new ArrayList<>();
    events.add(new Event("2021-03-11", "17:00", "Test Name", "Test Description", 2));

    Mockito.when(eventService.saveEvents(Mockito.any())).then(i -> i.getArguments()[0]);

    mvc.perform(
            post("/api/calendar")
                .content(mapper.writeValueAsString(events))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  public void createFaultyEventsTest() throws Exception {
    List<Event> faultyEvents = new ArrayList<>();
    faultyEvents.add(new Event("2021-03-40", "17:00", "Test Name", "Test Description", 2));
    faultyEvents.add(new Event("2021-03-11", "2500", "Test Name", "Test Description", 2));
    faultyEvents.add(new Event("2021-03-11", "17:00", "", "Test Description", 2));
    faultyEvents.add(new Event("2021-03-11", "17:00", "Test Name", "", 2));
    faultyEvents.add(new Event("2021-03-11", "17:00", "Test Name", "Test Description", -1));

    Mockito.when(eventService.saveEvents(Mockito.any())).then(i -> i.getArguments()[0]);

    mvc.perform(
            post("/api/calendar")
                .content(mapper.writeValueAsString(Collections.singletonList(faultyEvents.get(0))))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());

    mvc.perform(
            post("/api/calendar")
                .content(mapper.writeValueAsString(Collections.singletonList(faultyEvents.get(1))))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());

    mvc.perform(
            post("/api/calendar")
                .content(mapper.writeValueAsString(Collections.singletonList(faultyEvents.get(2))))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());

    mvc.perform(
            post("/api/calendar")
                .content(mapper.writeValueAsString(Collections.singletonList(faultyEvents.get(3))))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());

    mvc.perform(
            post("/api/calendar")
                .content(mapper.writeValueAsString(Collections.singletonList(faultyEvents.get(4))))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }
}
