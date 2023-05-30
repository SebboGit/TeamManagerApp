package edu.hm.cs.swe2.calendar.controllers;

import edu.hm.cs.swe2.calendar.models.Event;
import edu.hm.cs.swe2.calendar.service.EventService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Rollback
public class RetrieveEventControllerTest {

  @MockBean private EventService eventService;

  @Autowired private MockMvc mvc;

  private static List<Event> events;

  @BeforeAll
  static void startUp() {
    events = new ArrayList<>();
    events.add(new Event("2021-03-11", "1700", "Test Name", "Test Description", 1));
  }

  @Test
  public void getAllEventsTest() throws Exception {
    Mockito.when(eventService.findAllEvents()).thenReturn(events);

    mvc.perform(get("/api/calendar")).andExpect(status().isOk());
  }
}
