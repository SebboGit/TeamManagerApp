package edu.hm.cs.swe2.teamManager.services.infomaterial;

import edu.hm.cs.swe2.teamManager.database.InfomaterialRepository;
import edu.hm.cs.swe2.teamManager.models.Infomaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * Service class to implement repository functions to create a new infomaterial-entity.
 *
 * @author Michael Fortenbacher
 */

@Service
public class CreateInfomaterialService {
    @Autowired
    private InfomaterialRepository repository;

    /**
     *
     * @param infomaterial new infomaterial object that should be saved in the database
     * @param pdf the pdf data which is part of the infomaterial entity
     * @return the infomaterial that is stored in the database
     * @throws IOException
     */
    public Infomaterial saveInfomaterial(Infomaterial infomaterial, MultipartFile pdf) throws IOException {
        infomaterial.setPdf(pdf.getBytes());
        return repository.save(infomaterial);
    }
}
