package edu.hm.cs.swe2.teamManager.services.infomaterial;

import edu.hm.cs.swe2.teamManager.database.InfomaterialRepository;
import edu.hm.cs.swe2.teamManager.models.Infomaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class that calls and handels different methods for editing one or more infomaterials-entities
 *
 * @author Michael Fortenbacher
 */
@Service
public class EditInfomaterialService {

    @Autowired
    private InfomaterialRepository repository;


    /**
     * @param infoId  id of the infomaterial of which the name should be changed
     * @param newName new name for the infomaterial
     * @return changed infomaterial
     */
    public Infomaterial editInfoName(Integer infoId, String newName) {
        this.repository.editInfoName(infoId, newName);
        return this.repository.findById(infoId).get();
    }

    /**
     * @param infoId         id of the infomaterial of which the description should be changed
     * @param newDescription new description for the infomaterial
     * @return changed infomaterial
     */
    public Infomaterial editInfoDescription(Integer infoId, String newDescription) {
        this.repository.editInfoDescription(infoId, newDescription);
        return this.repository.findById(infoId).get();
    }

    /**
     * @param infoId id of the infomaterial of which the url should be changed
     * @param newUrl new url for the infomaterial
     * @return changed infomaterial
     */
    public Infomaterial editInfoUrl(Integer infoId, String newUrl) {
        this.repository.editInfoUrl(infoId, newUrl);
        return this.repository.findById(infoId).get();
    }

    /**
     * @param infoId id of the infomaterial of which the pdf should be changed
     * @param newPdf new pdf for the infomaterial
     * @return changed infomaterial
     */
    public Infomaterial editInfoPdf(Integer infoId, byte[] newPdf) {
        this.repository.editInfoPdf(infoId, newPdf);
        return this.repository.findById(infoId).get();
    }
}
