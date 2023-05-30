package edu.hm.cs.swe2.eventPictures.database;

import edu.hm.cs.swe2.eventPictures.models.EventPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * This class provides all the necessary methods for the API to manage the data between Entity and API.
 * It uses the methods from the EventPictureRepository to handle this data.
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
@Service
public class EventPictureService {

    @Autowired
    private EventPictureRepository eventPictureRepo;

    /**
     * This method returns all Event Pictures that are in the database.
     *
     * @return all Event Pictures
     */
    public List<EventPicture> getAllEventPicture() {
        return eventPictureRepo.findAll();
    }

    /**
     * This method returns an Event Picture which has the given ID.
     *
     * @param id ID from the Client side
     * @return an Event Pictures which have the given ID
     */
    public EventPicture getEventPicture(int id) {
        return eventPictureRepo.findById(id).get();
    }

    /**
     * This method returns all Event Pictures which have the given ID.
     *
     * @param eventId event ID from the Client side
     * @return List of Event Pictures which have the given event ID
     */
    public List<EventPicture> getByEventId(int eventId) {
        return eventPictureRepo.findByEvent(eventId);
    }

    /**
     * This method returns all Event Pictures which have the title.
     *
     * @param title title from the Client side
     * @return List of Event Pictures which have the given title
     */
    public List<EventPicture> getByTitle(String title) {
        return eventPictureRepo.findByTitle(title);
    }

    /**
     * This method returns all Event Pictures which have the description.
     *
     * @param description description from the Client side
     * @return List of Event Pictures which have the given description
     */
    public List<EventPicture> getByDescription(String description) {
        return eventPictureRepo.findByDescription(description);
    }

    /**
     * This method returns all Event Pictures which have the rating.
     *
     * @param rating rating from the Client side
     * @return List of Event Pictures which have the given rating
     */
    public List<EventPicture> getByRating(int rating) {
        return eventPictureRepo.findByRating(rating);
    }

    /**
     * This method gets an EventPicture object with the information and also a MultipartFile for the picture which should be uploaded.
     * The values of the MultipartFile are written in the EventPicture object via Setters.
     *
     * @param eventPicture includes the information values (e.g. title, description, etc.)
     * @param image        the image values which should be stored
     * @throws IOException throw exception when something is wrong with image methods (e.g. getBytes())
     */
    public void uploadEventPicture(EventPicture eventPicture, MultipartFile image) throws IOException {
        eventPicture.setPicName(image.getOriginalFilename());
        eventPicture.setPicType(image.getContentType());
        eventPicture.setPicData(image.getBytes());

        eventPictureRepo.save(eventPicture);
    }

    /**
     * This method gets the ID from the event picture, which should be deleted.
     *
     * @param id id to delete
     */
    public void deleteEventPicture(int id) {
        eventPictureRepo.deleteById(id);
    }


    /**
     * This method gets the ID from the event picture, which should be updated.
     *
     * @param id           id to update
     * @param eventPicture information to change
     * @param image        image to change
     * @return the new event picture
     * @throws IOException throw exception when something is wrong with the image methods (e.g. getBytes())
     */
    public EventPicture updateEventPicture(int id, EventPicture eventPicture, MultipartFile image) throws IOException {
        if (eventPicture.getDescription() != null) {
            this.eventPictureRepo.changeDescriptionById(id, eventPicture.getDescription());
        }
        if (eventPicture.getEvent() != null) {
            this.eventPictureRepo.changeEventById(id, eventPicture.getEvent());
        }
        //here the List<Integer> is changed to a String to be able to store it directly in the database
        if (eventPicture.getMembers() != null) {
            StringBuilder members = new StringBuilder();
            members.append(eventPicture.getMembers());
            members.deleteCharAt(0);
            members.deleteCharAt(members.length() - 1);
            String membersNew = members.toString();
            this.eventPictureRepo.changeMembersById(id, membersNew);
        }
        if (image != null) {
            this.eventPictureRepo.changeImageDataById(id, image.getBytes());
            this.eventPictureRepo.changeImageNameById(id, image.getOriginalFilename());
            this.eventPictureRepo.changeImageTypeById(id, image.getContentType());
        }
        if (eventPicture.getRating() != null) {
            this.eventPictureRepo.changeRatingById(id, eventPicture.getRating());
        }
        if (eventPicture.getTitle() != null) {
            this.eventPictureRepo.changeTitleById(id, eventPicture.getTitle());
        }

        return this.eventPictureRepo.findById(id).get();
    }

    /**
     * Checks if an Event Picture with the ID is present in the database.
     *
     * @param id ID from the Client side
     * @return if an Event Picture with given ID is present in the database
     */
    public boolean checkEventPictureId(int id) {
        return eventPictureRepo.existsById(id);
    }

    /**
     * Checks if an Event Picture with the given event ID is present in the database.
     *
     * @param eventId event ID from the Client side
     * @return if an Event Picture with given event ID is present in the database
     */
    public boolean checkEventPictureEventId(int eventId) {
        if (getByEventId(eventId).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if an Event Picture with the given title is present in the database.
     *
     * @param title title from the Client side
     * @return if an Event Picture with given title is present in the database
     */
    public boolean checkEventPictureTitle(String title) {
        if (getByTitle(title).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if an Event Picture with the given description is present in the database.
     *
     * @param description description from the Client side
     * @return if an Event Picture with given description is present in the database
     */
    public boolean checkEventPictureDescription(String description) {
        if (getByDescription(description).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if an Event Picture with the given rating is present in the database.
     *
     * @param rating rating from the Client side
     * @return if an Event Picture with given rating is present in the database
     */
    public boolean checkEventPictureRating(int rating) {
        if (getByRating(rating).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
