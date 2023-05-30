package edu.hm.cs.swe2.calendar.service;

import edu.hm.cs.swe2.calendar.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Spring service managing all event operations.
 *
 * @author Sebastian Theimer
 */
@Service
public class EventService {

  private final CalendarRepository repo;

  @Autowired
  public EventService(CalendarRepository repo) {
    this.repo = repo;
  }

  public List<Event> saveEvents(Iterable<Event> events) {
    return repo.saveAll(events);
  }

  public List<Event> findAllEvents() {
    return repo.findAll();
  }

  public int findLatestEventId() {
    return repo.findLatestEventId();
  }

  /**
   * if event is present, returns the event else null
   *
   * @param id database
   * @return event or null if not event with id is existing
   */
  public Event findById(Integer id) {
    Optional<Event> ev = repo.findById(id);

    return ev.orElse(null);
  }

  public Event findByEventName(String name) {
    return repo.findByName(name);
  }

  public List<Event> findByDate(String date) {
    return repo.findByDate(date);
  }

  /**
   * receives an optional and checks if event is present before executing the update
   *
   * @param date date string
   * @param id database id
   * @return Event event
   */
  public Event updateEventDateById(String date, int id) {
    Optional<Event> eventContainer = repo.findById(id);

    if (eventContainer.isPresent()) {
      repo.updateEventDateById(date, id);
      return eventContainer.get();
    }
    return null;
  }

  /**
   * receives an optional and checks if event is present before executing the update
   *
   * @param time time string
   * @param id database id
   * @return Event event
   */
  public Event updateEventTimeById(String time, int id) {
    Optional<Event> eventContainer = repo.findById(id);

    if (eventContainer.isPresent()) {
      repo.updateEventTimeById(time, id);
      return eventContainer.get();
    }
    return null;
  }

  /**
   * receives an optional and checks if event is present before executing the update
   *
   * @param name name
   * @param id database id
   * @return Event event
   */
  public Event updateEventNameById(String name, int id) {
    Optional<Event> eventContainer = repo.findById(id);

    if (eventContainer.isPresent()) {
      repo.updateEventNameById(name, id);
      return eventContainer.get();
    }
    return null;
  }

  /**
   * receives an optional and checks if event is present before executing the update
   *
   * @param description event description
   * @param id database id
   * @return Event event
   */
  public Event updateEventDescriptionById(String description, int id) {
    Optional<Event> eventContainer = repo.findById(id);

    if (eventContainer.isPresent()) {
      repo.updateEventDescriptionById(description, id);
      return eventContainer.get();
    }
    return null;
  }

  public void deleteEvent(int id) {
    repo.deleteById(id);
  }

  public void uploadPDF(Event event, MultipartFile pdf) throws IOException {
    event.setProgram(pdf.getBytes());
    repo.save(event);
  }
}
