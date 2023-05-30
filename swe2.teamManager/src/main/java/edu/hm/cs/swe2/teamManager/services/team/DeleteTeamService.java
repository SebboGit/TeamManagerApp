package edu.hm.cs.swe2.teamManager.services.team;

import edu.hm.cs.swe2.teamManager.database.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class to implement repository functions to delete a team-entity
 *
 * @author Michael Fortenbacher
 */
@Service
public class DeleteTeamService {

    @Autowired
    private TeamRepository repository;

    /**
     * deletes a team specified by its Id
     *
     * @param teamID Id of the team that should be deleted
     */
    public void deleteTeam(Integer teamID) {
        this.repository.deleteById(teamID);
    }

    /**
     * deletes all teams that are in the database
     */
    public void deleteAllTeams() {
        this.repository.deleteAll();
    }
}
