package edu.hm.cs.swe2.teamManager.controller.team;

import edu.hm.cs.swe2.teamManager.controller.InputValidator;
import edu.hm.cs.swe2.teamManager.models.Team;
import edu.hm.cs.swe2.teamManager.services.team.ReadTeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Controller that is used when reading one or more sports teams from the database.
 *
 * @author Michael Fortenbacher
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Endpoint for reading sports teams from the database", tags = {"Get a team"})
@RequestMapping(path = "api/v1.0/team")
public class ReadTeamController {

    @Autowired
    private ReadTeamService service;

    @Autowired
    private InputValidator validator;

    @ApiOperation(value = "Get a sports team by its Id")
    @GetMapping(value = "{teamId}")
    public Team getTeamById(@PathVariable("teamId") Integer teamId) {
        validator.checkId(teamId, "ReadTeamController: getTeamById()");
        return this.service.findById(teamId);
    }

    //Object[] is returned as a workaround to avoid errors in the frontend when there are no teams in the database
    @ApiOperation(value = "Get all sports teams that are in the database")
    @GetMapping
    public Object[] getAllTeams() {
        List<Team> result = this.service.findAllTeams();
        return result.toArray();
    }

    @ApiOperation(value = "Get the if of the sports team that was added as last one to the database")
    @GetMapping("latestTeamId")
    public Integer getLatestTeamId() {
        return this.service.findLatestTeamId();
    }
}
