package edu.hm.cs.swe2.teamManager.services.infomaterial;


import edu.hm.cs.swe2.teamManager.database.InfomaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class to implement repository functions to delete a infomaterial-entity
 *
 * @author Michael Fortenbacher
 */
@Service
public class DeleteInfomaterialService {

    @Autowired
    private InfomaterialRepository repository;

    /**
     * deletes a infomaterial specified by its id
     *
     * @param infoId Id of the infomaterial that should be deleted
     */
    public void deleteInfomaterial(Integer infoId) {
        this.repository.deleteById(infoId);
    }


    /**
     * deletes all infomaterials from the database
     */
    public void deleteAllInfomaterials() {
        this.repository.deleteAll();
    }
}
