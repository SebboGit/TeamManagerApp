package edu.hm.cs.swe2.eventPictures.database;

import edu.hm.cs.swe2.eventPictures.models.EventPicture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


/**
 * This is the test class for the Database functionality.
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
@DataJpaTest
public class EventPictureDatabaseTest {

    @Autowired
    private EventPictureRepository eventPictureRepository;

    @Autowired
    private TestEntityManager entityManager;

    /**
     * List with Event Pictures for testing
     */
    private List<EventPicture> eventPictureList = Arrays.asList(new EventPicture[]{new EventPicture("test_title1", "test_description1", 3, 22),
            new EventPicture("test_title2", "test_description2", 1, 22),
            new EventPicture("test_title3", "test_description3", 5, 33),
            new EventPicture("test_title1", "test_description2", 3, 44)});

    /**
     * Array to store the IDs
     */
    private int[] ids = new int[]{0, 0, 0, 0};

    /**
     * store the Event Pictures and write the IDs in the array
     */
    @BeforeEach
    void beforeEach() {
        ids[0] = (int) entityManager.persistAndGetId(eventPictureList.get(0));
        ids[1] = (int) entityManager.persistAndGetId(eventPictureList.get(1));
        ids[2] = (int) entityManager.persistAndGetId(eventPictureList.get(2));
        ids[3] = (int) entityManager.persistAndGetId(eventPictureList.get(3));
    }

    /**
     * test the changing method for the description
     */
    @Test
    void testChangeDescriptionById() {
        this.eventPictureRepository.changeDescriptionById(ids[1], "description_changed");
        entityManager.clear();
        EventPicture changedValue = this.eventPictureRepository.findById(ids[1]).get();

        assertThat(changedValue).hasFieldOrPropertyWithValue("description", "description_changed");
    }

    /**
     * test the changing method for the event
     */
    @Test
    void testChangeEventById() {
        this.eventPictureRepository.changeEventById(ids[1], 30);
        entityManager.clear();
        EventPicture changedValue = this.eventPictureRepository.findById(ids[1]).get();

        assertThat(changedValue).hasFieldOrPropertyWithValue("event", 30);
    }

    /**
     * test the changing method for the members
     */
    @Test
    void testChangeMembersById() {
        this.eventPictureRepository.changeMembersById(ids[1], "1, 2, 3");
        entityManager.clear();
        EventPicture changedValue = this.eventPictureRepository.findById(ids[1]).get();
        List<Integer> expectedValue = Arrays.asList(1, 2, 3);

        assertThat(changedValue).hasFieldOrPropertyWithValue("members", expectedValue);
    }

    /**
     * test the changing method for the image data
     */
    @Test
    void testChangeImageDataById() {
        byte[] picData = "Hello World".getBytes();
        this.eventPictureRepository.changeImageDataById(ids[1], picData);
        entityManager.clear();
        EventPicture changedValue = this.eventPictureRepository.findById(ids[1]).get();

        assertThat(changedValue).hasFieldOrPropertyWithValue("picData", picData);
    }

    /**
     * test the changing method for the image name
     */
    @Test
    void testChangeImageNameById() {
        this.eventPictureRepository.changeImageNameById(ids[1], "test_image_name");
        entityManager.clear();
        EventPicture changedValue = this.eventPictureRepository.findById(ids[1]).get();

        assertThat(changedValue).hasFieldOrPropertyWithValue("picName", "test_image_name");
    }

    /**
     * test the changing method for the image type
     */
    @Test
    void testChangeImageTypeById() {
        this.eventPictureRepository.changeImageTypeById(ids[1], "multipart/form-data");
        entityManager.clear();
        EventPicture changedValue = this.eventPictureRepository.findById(ids[1]).get();

        assertThat(changedValue).hasFieldOrPropertyWithValue("picType", "multipart/form-data");
    }

    /**
     * test the changing method for the rating number
     */
    @Test
    void testChangeRatingById() {
        this.eventPictureRepository.changeRatingById(ids[1], 3);
        entityManager.clear();
        EventPicture changedValue = this.eventPictureRepository.findById(ids[1]).get();

        assertThat(changedValue).hasFieldOrPropertyWithValue("rating", 3);
    }

    /**
     * test the changing method for the title
     */
    @Test
    void testChangeTitleById() {
        this.eventPictureRepository.changeTitleById(ids[1], "anniversary");
        entityManager.clear();
        EventPicture changedValue = this.eventPictureRepository.findById(ids[1]).get();

        assertThat(changedValue).hasFieldOrPropertyWithValue("title", "anniversary");
    }

    /**
     * test the method which gets all Event Pictures out of the database
     */
    @Test
    void testGetAllEventPicture() {
        List<EventPicture> value = this.eventPictureRepository.findAll();

        assertThat(value).hasSize(4).contains(eventPictureList.get(0), eventPictureList.get(1), eventPictureList.get(2));
    }

    /**
     * test the method which gets an Event Picture with a given ID
     */
    @Test
    void testGetEventPicture() {
        EventPicture value = this.eventPictureRepository.findById(ids[0]).get();

        assertThat(value).isEqualTo(eventPictureList.get(0));
    }

    /**
     * test the method to get all Event Pictures with the event ID
     */
    @Test
    void testFindByEvent() {
        List<EventPicture> eventIdValues = this.eventPictureRepository.findByEvent(22);
        List<EventPicture> eventIdValueNull = this.eventPictureRepository.findByEvent(25);

        assertThat(eventIdValues).hasSize(2).contains(eventPictureList.get(0), eventPictureList.get(1));
        assertTrue(eventIdValueNull.isEmpty());

    }

    /**
     * test the method to get all Event Pictures with the title
     */
    @Test
    void testFindByTitle() {
        List<EventPicture> titleValues = this.eventPictureRepository.findByTitle("test_title1");
        List<EventPicture> titleValueNull = this.eventPictureRepository.findByTitle("test_title50");

        assertThat(titleValues).hasSize(2).contains(eventPictureList.get(0), eventPictureList.get(3));
        assertTrue(titleValueNull.isEmpty());
    }

    /**
     * test the method to get all Event Pictures with the description
     */
    @Test
    void testFindByDescription() {
        List<EventPicture> descriptionValue = this.eventPictureRepository.findByDescription("test_description2");
        List<EventPicture> descriptionValueNull = this.eventPictureRepository.findByDescription("test_description50");

        assertThat(descriptionValue).hasSize(2).contains(eventPictureList.get(1), eventPictureList.get(3));
        assertTrue(descriptionValueNull.isEmpty());
    }

    /**
     * test the method to get all Event Pictures with the rating
     */
    @Test
    void testFindByRating() {
        List<EventPicture> ratingValue = this.eventPictureRepository.findByRating(3);
        List<EventPicture> ratingValueNull = this.eventPictureRepository.findByRating(2);

        assertThat(ratingValue).hasSize(2).contains(eventPictureList.get(0), eventPictureList.get(3));
        assertTrue(ratingValueNull.isEmpty());
    }

    /**
     * test the method to upload a new Event Picture
     *
     * @throws IOException if the InputStream have no/wrong input
     */
    @Test
    void testUploadEventPicture() throws IOException {
        InputStream data = getClass().getClassLoader().getResourceAsStream("test_jpeg.jpg");
        MockMultipartFile file = new MockMultipartFile("uploaded-file", "test_jpeg.jpg", "multipart/form-data", data);
        eventPictureList.get(0).setPicData(file.getBytes());
        eventPictureList.get(0).setPicType(file.getContentType());
        eventPictureList.get(0).setPicName(file.getName());

        this.eventPictureRepository.save(eventPictureList.get(0));
        EventPicture value = this.eventPictureRepository.findById(ids[0]).get();

        assertThat(value).isEqualTo(eventPictureList.get(0));
    }

    /**
     * test the method to delete an Event Picture with a given ID
     */
    @Test
    void testDeleteEventPicture() {
        EventPicture eventPicture = this.eventPictureRepository.findById(ids[2]).get();

        this.eventPictureRepository.deleteById(ids[2]);
        assertThat(eventPicture).isEqualTo(eventPictureList.get(2));
        assertThrows(NoSuchElementException.class, () -> this.eventPictureRepository.findById(ids[2]).get());
    }

    /**
     * test the method to check if an Event Picture with a given ID is present in the database
     */
    @Test
    void testCheckEventPictureId() {
        assertTrue(this.eventPictureRepository.existsById(ids[0]));
        assertFalse(this.eventPictureRepository.existsById(1000));
    }
}
