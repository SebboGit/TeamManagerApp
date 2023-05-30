package edu.hm.cs.swe2.teamManager.services.team;

import edu.hm.cs.swe2.teamManager.database.TeamRepository;
import edu.hm.cs.swe2.teamManager.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class that calls and handels different methods for editing one or more team-entities
 *
 * @author Michael Fortenbacher
 */
@Service
public class EditTeamService {

    @Autowired
    private TeamRepository repository;

    /**
     * Method that calls the repository function to edit the memberIds of a team
     *
     * @param teamId    Id of the team of which the memberIds should be changed
     * @param memberIds new String representing teh changed memberIds
     * @return changed team
     */
    public Team editMemberIds(Integer teamId, String memberIds) {
        this.repository.editMemberIds(teamId, memberIds);
        updateMemberCount(teamId);
        return this.repository.findById(teamId).get();
    }

    /**
     * @param teamId   Id of the team where a member should be added
     * @param memberId Id of the member that will be added
     * @return changed team
     */
    public Team addTeamMember(Integer teamId, Integer memberId) {
        this.repository.addTeamMember(teamId, memberId);
        updateMemberCount(teamId);
        return this.repository.findById(teamId).get();
    }

    /**
     * Method that calls the repository function to edit the name of a team
     *
     * @param teamId   Id of the team of which the memberIds should be changed
     * @param teamName new String representing the changed memberIds
     * @return changed team
     */
    public Team editTeamName(Integer teamId, String teamName) {
        this.repository.editTeamName(teamId, teamName);
        return this.repository.findById(teamId).get();
    }

    /**
     * Method that calls the repository function to edit the infomaterialIds of a team
     *
     * @param teamId          Id of the team of which the memberIds should be changed
     * @param infomaterialIds new String representing the changed infomaterialIds
     * @return changed team
     */
    public Team editInfomaterialIds(Integer teamId, String infomaterialIds) {
        this.repository.editInfomaterialIds(teamId, infomaterialIds);
        updateInfomaterialCount(teamId);
        return this.repository.findById(teamId).get();
    }

    /**
     * @param teamId         Id of the team where a infomaterial should be added
     * @param infomaterialId Id of the infomaterial that will be added
     * @return changed team
     */
    public Team addInfomaterial(Integer teamId, Integer infomaterialId) {
        this.repository.addInfomaterial(teamId, infomaterialId);
        updateInfomaterialCount(teamId);
        return this.repository.findById(teamId).get();
    }

    /**
     * private method that is used to update the memberCount of  team to a correct amount after memberIds have been edited
     *
     * @param teamId Id of the team which was edited
     */
    private void updateMemberCount(Integer teamId) {
        Team editedTeam = this.repository.findById(teamId).get();
        this.repository.updateMemberCount(teamId, editedTeam.getMemberCount());
    }

    /**
     * private method that is used to update the infomaterialCount of  team to a correct amount after infomaterialIds have been edited
     *
     * @param teamId Id of the team which was edited
     */
    private void updateInfomaterialCount(Integer teamId) {
        Team editedTeam = this.repository.findById(teamId).get();
        this.repository.updateInfomaterialCount(teamId, editedTeam.getInfomaterialCount());
    }
}
