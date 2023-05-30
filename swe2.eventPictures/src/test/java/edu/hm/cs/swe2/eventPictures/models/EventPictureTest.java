package edu.hm.cs.swe2.eventPictures.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * This is the test class for the EventPicture.
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
public class EventPictureTest {

    /**
     * test the method which gets the member IDs with the right format
     */
    @Test
    void testGetMembersRightFormat() {
        List<Integer> memberID = Arrays.asList(1, 2, 3, 4);
        EventPicture eventPicture = new EventPicture("test_title", "test_description", 1, "1, 2, 3, 4", 2);
        assertEquals(eventPicture.getMembers(), memberID);
    }

    /**
     * test the method which gets the member IDs with the false format
     */
    @Test
    void testGetMembersFalseFormat() {
        EventPicture eventPicture = new EventPicture("test_title", "test_description", 1, "1,2,3,4", 2);
        assertThrows(NumberFormatException.class, eventPicture::getMembers);
    }

    /**
     * test the method which sets the member IDs
     */
    @Test
    void testSetMembers() {
        List<Integer> memberID = Arrays.asList(1, 2, 3, 4);
        EventPicture eventPicture = new EventPicture();
        eventPicture.setMembers(memberID);
        assertEquals(memberID, eventPicture.getMembers());
    }
}
