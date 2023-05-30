package edu.hm.cs.swe2.teamManager.controller.team;

import edu.hm.cs.swe2.teamManager.controller.InputValidator;
import edu.hm.cs.swe2.teamManager.services.team.DeleteTeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

/**
 * Rest Controller that is used when a sports team should be deleted from the database
 *
 * @author Michael Fortenbacher
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Endpoint for deleting a sports team", tags = {"Delete a team"})
@RequestMapping(path = "api/v1.0/team")
public class DeleteTeamController {

    @Autowired
    private DeleteTeamService service;

    @Autowired
    private InputValidator validator;

    /**
     * Endpoint for deleting a team with a given id
     *
     * @param teamId Id of the team
     * @throws NoSuchElementException if the Id doesn't exist in the database
     */
    @ApiOperation(value = "delete a team")
    @DeleteMapping(value = "{teamId}")
    public ResponseEntity<String> deleteTeam(@PathVariable("teamId") Integer teamId) {
        validator.checkId(teamId, "DeleteTeamController: deleteTeam()");

        try {
            this.service.deleteTeam(teamId);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException();
        }

        return new ResponseEntity<>(String.format("Team with the Id '%d' has been deleted.", teamId), HttpStatus.OK);
    }

    /**
     * Endpoint for deleting a all teams that are saved in the database
     */
    @ApiOperation(value = "delete all teams")
    @DeleteMapping
    public ResponseEntity<String> deleteAllTeams() {
        this.service.deleteAllTeams();
        return new ResponseEntity<>(String.format("All teams within the database have been deleted"), HttpStatus.OK);
    }
}
