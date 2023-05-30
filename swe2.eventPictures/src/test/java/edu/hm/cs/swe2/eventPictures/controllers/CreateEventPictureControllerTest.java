package edu.hm.cs.swe2.eventPictures.controllers;

import edu.hm.cs.swe2.eventPictures.database.EventPictureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This is the test class for the CreateEventPictureController.
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
@WebMvcTest(CreateEventPictureController.class)
public class CreateEventPictureControllerTest {

    @MockBean
    EventPictureService eventPictureService;

    @MockBean
    DataValidation dataValidation;

    @Autowired
    WebApplicationContext webApplicationContext;

    /**
     * this test checks if the Event Picture upload works correct with a multipart/form-data and information
     *
     * @throws Exception at the perform() method
     */
    @Test
    void testUploadEventPicture() throws Exception {
        when(dataValidation.bindingFailure(any())).thenReturn(true);
        when(dataValidation.falseRatingNumber(anyInt())).thenReturn(true);
        String params = "?title=test_title&description=test_description&rating=3&event=1&members=45";
        MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.MULTIPART_FORM_DATA_VALUE, "Hello, World!".getBytes());

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/api/v1/EventPictures/upload" + params).file(file)).andExpect(status().isCreated());
    }
}
