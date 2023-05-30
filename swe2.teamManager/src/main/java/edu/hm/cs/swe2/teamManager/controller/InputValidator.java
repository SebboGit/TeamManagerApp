package edu.hm.cs.swe2.teamManager.controller;

import edu.hm.cs.swe2.teamManager.models.Infomaterial;
import edu.hm.cs.swe2.teamManager.models.Team;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * validator class to test if inputs are correct and otherwise throws exceptions with the given message
 *
 * @author Michael Fortenbacher
 */
@Component
public class InputValidator {

    public void checkId(Integer id, String location) {
        try {
            if (id < 1) {
                throw new IllegalArgumentException(String.format("Value given for Id was negative: '%d' " + " Error occurred in: " + location, id));
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("No Id was given! Error occurred in: " + location);
        }
    }

    public void checkString(String stringToTest, String location) {
        try {
            if (stringToTest.equals("undefined")||stringToTest.equals("")) {
                throw new IllegalArgumentException("Given String undefined or empty: " + stringToTest + " Error occurred in: " + location);
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Given String was null. Error occurred in: " + location);
        }
    }

    public void checkPdfUpload(MultipartFile pdf, String location) throws IOException {
        try {
            //length of 9 means no pdf was uploaded
            if (pdf.getBytes().length == 9) {
                throw new IllegalArgumentException("No File was found or uploaded: " + pdf + " Error occurred in: " + location);
            }
        } catch (IOException e) {
            throw new IOException("Given pdf is null. Error occurred in: " + location);
        }
    }

    public void checkIdsString(String idString, String location) {
        for (String id : idString.split(",")) {
            try {
                Integer.parseInt(id);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("The List of Ids did contain incorrect values!: " + idString + " Error occurred in: " + location);
            }
        }
    }

    public void checkTeam(Team team, String location) {
        if (team == null) {
            throw new IllegalArgumentException("The given team was null ! " + "Error occurred in: " + location);
        } else {
            try {
                this.checkId(team.getId(), location);
                this.checkIdsString(team.getMemberIds(), location);
                this.checkIdsString(team.getInfomaterialIds(), location);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Error regarding the following team: " + team.toString() + " " + e.getMessage());
            } catch (NullPointerException e) {
                throw new NullPointerException("Error regarding the following Team" + team.toString() + " " + e.getMessage());
            }
        }
    }

    public void checkInfomaterial(Infomaterial info, String location) {
        if (info == null) {
            throw new IllegalArgumentException("The given infomaterial was null! " + "Error occurred in: " + location);
        } else {
            try {
                this.checkId(info.getId(), location);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Error regarding the following Infomaterial: " + info.toString() + " " + e.getMessage());
            }
        }
    }
}
