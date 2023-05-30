package edu.hm.cs.swe2.teamManager.controller.team;

import edu.hm.cs.swe2.teamManager.controller.InputValidator;
import edu.hm.cs.swe2.teamManager.models.Team;
import edu.hm.cs.swe2.teamManager.services.team.CreateTeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller that is used when creating a new sports team
 *
 * @author Michael Fortenbacher
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Endpoint for creating a new sports team", tags = {"Create a team"})
@RequestMapping(path = "api/v1.0/team")
public class CreateTeamController {

    @Autowired
    private CreateTeamService service;

    @Autowired
    private InputValidator validator;

    /**
     * Endpoint to add a single team.
     * A POST method is used and the attributes name and memberIDs as an Integer-List are expected
     *
     * @param team team that will be created/saved
     */
    @ApiOperation(value = "Create a new team")
    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        validator.checkString(team.getName(), "CreateTeamController: createTeam()");
        validator.checkIdsString(team.getMemberIds(), "CreateTeamController: createTeam() for memberIds");
        validator.checkIdsString(team.getInfomaterialIds(), "CreateTeamController: createTeam() for infomaterialIds");
        return new ResponseEntity<>(this.service.saveTeam(team), HttpStatus.CREATED);
    }
}
