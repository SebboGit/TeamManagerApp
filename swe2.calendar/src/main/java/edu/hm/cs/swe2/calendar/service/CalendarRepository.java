package edu.hm.cs.swe2.calendar.service;

import edu.hm.cs.swe2.calendar.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Service
public interface CalendarRepository extends JpaRepository<Event, Integer> {

  Event findByName(String name);

  List<Event> findByDate(String date);

  @Query(nativeQuery = true, value = "SELECT e.id FROM Event e ORDER BY e.id DESC LIMIT 1")
  int findLatestEventId();

  @Modifying
  @Query("UPDATE Event e SET e.date = ?1 WHERE e.id = ?2")
  void updateEventDateById(String date, int id);

  @Modifying
  @Query("UPDATE Event e SET e.time = ?1 WHERE e.id = ?2")
  void updateEventTimeById(String time, int id);

  @Modifying
  @Query("UPDATE Event e SET e.name = ?1 WHERE e.id = ?2")
  void updateEventNameById(String name, int id);

  @Modifying
  @Query("UPDATE Event e SET e.description = ?1 WHERE e.id = ?2")
  void updateEventDescriptionById(String description, int id);
}
