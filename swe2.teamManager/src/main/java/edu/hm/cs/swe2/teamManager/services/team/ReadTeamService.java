package edu.hm.cs.swe2.teamManager.services.team;

import edu.hm.cs.swe2.teamManager.database.TeamRepository;
import edu.hm.cs.swe2.teamManager.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;


/**
 * Service class to implement repository functions to retrieve team-entities from the database.
 *
 * @author Michael Fortenbacher
 */
@Service
public class ReadTeamService {

    @Autowired
    private TeamRepository repository;


    /**
     * @param teamId Id of the team entity that should be retrieved
     * @return a team entity
     * @throws EntityNotFoundException if the entity is not found
     */
    public Team findById(Integer teamId) throws EntityNotFoundException {
        return repository.findById(teamId).get();
    }

    public List<Team> findAllTeams() {
        return repository.findAll();
    }

    public Integer findLatestTeamId() {
        return repository.getLatestTeamId();
    }
}
