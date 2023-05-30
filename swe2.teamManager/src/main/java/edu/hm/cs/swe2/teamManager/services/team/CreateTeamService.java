package edu.hm.cs.swe2.teamManager.services.team;

import edu.hm.cs.swe2.teamManager.database.TeamRepository;
import edu.hm.cs.swe2.teamManager.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class to implement repository functions to create a new team-entity.
 *
 * @author Michael Fortenbacher
 */

@Service
public class CreateTeamService {

    @Autowired
    private TeamRepository repository;

    /**
     * @param team a team-entity that should be added
     * @return the team that got added
     */
    public Team saveTeam(Team team) {
        return repository.save(team);
    }
}
