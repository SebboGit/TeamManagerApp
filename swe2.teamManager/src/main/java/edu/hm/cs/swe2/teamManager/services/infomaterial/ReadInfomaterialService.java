package edu.hm.cs.swe2.teamManager.services.infomaterial;


import edu.hm.cs.swe2.teamManager.database.InfomaterialRepository;
import edu.hm.cs.swe2.teamManager.models.Infomaterial;
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
public class ReadInfomaterialService {

    @Autowired
    private InfomaterialRepository repository;

    public Infomaterial findInfoById(Integer infoId) throws EntityNotFoundException {
        return repository.findById(infoId).get();
    }

    public List<Infomaterial> findAllInfo() {
        return repository.findAll();
    }
}
