package edu.hm.cs.swe2.eventPictures.database;

import java.util.List;

import edu.hm.cs.swe2.eventPictures.models.EventPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * The interface for the EventPictureService
 * provides the methods for managing the database entity.
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
@Repository
public interface EventPictureRepository extends JpaRepository<EventPicture, Integer> {

    /**
     * A query that returns all Event Pictures which have the given event ID
     *
     * @param eventId event ID from the Client side
     * @return List of Event Pictures which have the given event ID
     */
    @Transactional
    List<EventPicture> findByEvent(int eventId);

    /**
     * A query that returns all Event Pictures which have the given title
     *
     * @param title title from the Client side
     * @return List of Event Pictures which have the given title
     */
    @Transactional
    List<EventPicture> findByTitle(String title);

    /**
     * A query that returns all Event Pictures which have the given description
     *
     * @param description description from the Client side
     * @return List of Event Pictures which have the given description
     */
    @Transactional
    List<EventPicture> findByDescription(String description);

    /**
     * A query that returns all Event Pictures which have the given rating
     *
     * @param rating rating from the Client side
     * @return List of Event Pictures which have the given rating
     */
    @Transactional
    List<EventPicture> findByRating(int rating);

    /**
     * A query to change the description
     *
     * @param id          id to change
     * @param description new description to update
     */
    @Modifying
    @Transactional
    @Query("UPDATE EventPicture ep SET ep.description = ?2 WHERE ep.id = ?1")
    void changeDescriptionById(int id, String description);

    /**
     * A query to change the Event
     *
     * @param id    id to change
     * @param event new event to update
     */
    @Modifying
    @Transactional
    @Query("UPDATE EventPicture ep SET ep.event = ?2 WHERE ep.id = ?1")
    void changeEventById(int id, int event);

    /**
     * A query to change the Members
     *
     * @param id      id to change
     * @param members new members to update
     */
    @Modifying
    @Transactional
    @Query("UPDATE EventPicture ep SET ep.members = ?2 WHERE ep.id = ?1")
    void changeMembersById(int id, String members);

    /**
     * A query to change the image data
     *
     * @param id        id to change
     * @param imageData new image data to update
     */
    @Modifying
    @Transactional
    @Query("UPDATE EventPicture ep SET ep.picData = ?2 WHERE ep.id = ?1")
    void changeImageDataById(int id, byte[] imageData);

    /**
     * A query to change the image name
     *
     * @param id        id to change
     * @param imageName new image name to update
     */
    @Modifying
    @Transactional
    @Query("UPDATE EventPicture ep SET ep.picName = ?2 WHERE ep.id = ?1")
    void changeImageNameById(int id, String imageName);

    /**
     * A query to change the image type
     *
     * @param id        id to change
     * @param imageType new image type to update
     */
    @Modifying
    @Transactional
    @Query("UPDATE EventPicture ep SET ep.picType = ?2 WHERE ep.id = ?1")
    void changeImageTypeById(int id, String imageType);

    /**
     * A query to change the rating
     *
     * @param id     id to change
     * @param rating new rating to update
     */
    @Modifying
    @Transactional
    @Query("UPDATE EventPicture ep SET ep.rating = ?2 WHERE ep.id = ?1")
    void changeRatingById(int id, int rating);

    /**
     * A query to change the title
     *
     * @param id    id to change
     * @param title new title to update
     */
    @Modifying
    @Transactional
    @Query("UPDATE EventPicture ep SET ep.title = ?2 WHERE ep.id = ?1")
    void changeTitleById(int id, String title);
}
