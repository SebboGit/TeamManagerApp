package edu.hm.cs.swe2.teamManager.controller.team;

import edu.hm.cs.swe2.teamManager.controller.InputValidator;
import edu.hm.cs.swe2.teamManager.models.Team;
import edu.hm.cs.swe2.teamManager.services.team.EditTeamService;
import edu.hm.cs.swe2.teamManager.services.team.ReadTeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller used for editing different properties of a team
 *
 * @author Michael Fortenbacher
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Endpoint for editing a existing sports team", tags = {"Edit a team"})
@RequestMapping(path = "api/v1.0/team")
public class EditTeamController {

    @Autowired
    private EditTeamService service;
    @Autowired
    private ReadTeamService readService;

    @Autowired
    private InputValidator validator;

    /**
     * Endpoint where the String of memberIds of an existing team can be changed all at once
     *
     * @param teamId    Id of the team that should be edited
     * @param memberIds new value/String for the new memberIds
     * @return edited team together with the HTTP Code 200
     */
    @ApiOperation(value = "Edit all memberIds of a existing team at once")
    @PutMapping("memberIds")
    public ResponseEntity<Team> editMemberIds(Integer teamId, String memberIds) {
        validator.checkId(teamId, "EditTeamController: editMemberIds()");
        validator.checkIdsString(memberIds, "EditTeamController: editMemberIds()");
        return new ResponseEntity<>(this.service.editMemberIds(teamId, memberIds), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new member to an a existing team")
    @PutMapping("addMember")
    public ResponseEntity<Team> addTeamMember(Integer teamId, Integer memberId) {
        validator.checkId(teamId, "EditTeamController: addTeamMembers() for teamId");
        validator.checkId(memberId, "EditTeamController: addTeamMembers() for memberId");
        return new ResponseEntity<>(this.service.addTeamMember(teamId, memberId), HttpStatus.OK);
    }

    /**
     * Endpoint where the name of an existing team can be changed
     *
     * @param teamId   Id of the team that should be edited
     * @param teamName new name for the sports team
     * @return edited team together with the HTTP Code 200
     */
    @ApiOperation(value = "Edit the name of an existing team")
    @PutMapping(value = "teamName")
    public ResponseEntity<Team> editTeamName(Integer teamId, String teamName) {
        validator.checkId(teamId, "EditTeamController: editTeamName()");
        return new ResponseEntity<>(this.service.editTeamName(teamId, teamName), HttpStatus.OK);
    }

    /**
     * Endpoint where the String of infomaterialIds of an existing team can be changed all at once
     *
     * @param teamId          Id of the team that should be edited
     * @param infomaterialIds new value/String for the new infomaterialIds
     * @return edited team together with the HTTP Code 200
     */
    @ApiOperation(value = "Edit all infomaterialIds of a existing team at once")
    @PutMapping("infomaterialIds")
    public ResponseEntity<Team> editInfomaterialIds(Integer teamId, String infomaterialIds) {
        validator.checkId(teamId, "EditTeamController: editInfomaterials()");
        validator.checkIdsString(infomaterialIds, "EditTeamController: editInfomaterials()");
        return new ResponseEntity<>(this.service.editInfomaterialIds(teamId, infomaterialIds), HttpStatus.OK);
    }

    /**
     * Endpoint where a single infomaterial can be added via its id to a team
     *
     * @param teamId         id of the team
     * @param infomaterialId if of the infomaterial
     * @return edited team together with the HTTP Code 200
     */
    @ApiOperation(value = "Add a new infomaterial to an a existing team")
    @PutMapping("addInfomaterial")
    public ResponseEntity<Team> addInfomaterial(Integer teamId, Integer infomaterialId) {
        validator.checkId(teamId, "EditTeamController: addInfomaterial() for teamId");
        validator.checkId(infomaterialId, "EditTeamController: addInfomaterial() for infomaterialId");
        return new ResponseEntity<>(this.service.addInfomaterial(teamId, infomaterialId), HttpStatus.OK);
    }

    /**
     * @param team changes in form of a new teamn
     */
    @PutMapping("changeTeam")
    public ResponseEntity<Team> changeTeam(@RequestBody Team team) {
        validator.checkId(team.getId(), "EditTeamController: changeTeam() for teamId");
        int teamId = team.getId();
        validator.checkString(team.getName(), "EditTeamController: changeTeam()");
        validator.checkIdsString(team.getMemberIds(), "EditTeamController: changeTeam() for memberIds");
        validator.checkIdsString(team.getInfomaterialIds(), "EditTeamController: changeTeam() for infomaterialIds");
        editTeamName(teamId, team.getName());
        editMemberIds(teamId, team.getMemberIds());
        editInfomaterialIds(teamId, team.getInfomaterialIds());
        //response is still the old team at the moment
        return new ResponseEntity<>(this.readService.findById(teamId), HttpStatus.OK);
    }

}
