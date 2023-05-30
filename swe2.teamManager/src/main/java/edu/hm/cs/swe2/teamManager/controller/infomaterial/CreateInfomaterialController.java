package edu.hm.cs.swe2.teamManager.controller.infomaterial;

import edu.hm.cs.swe2.teamManager.controller.InputValidator;
import edu.hm.cs.swe2.teamManager.models.Infomaterial;
import edu.hm.cs.swe2.teamManager.services.infomaterial.CreateInfomaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Rest Controller that is used when creating a new infomaterial
 *
 * @author Michael Fortenbacher
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Endpoint for creating a new infomaterial", tags = {"Create a infomaterial"})
@RequestMapping(path = "api/v1.0/infomaterial")
public class CreateInfomaterialController {

    @Autowired
    private CreateInfomaterialService service;

    @Autowired
    private InputValidator validator;

    /**
     * Endpoint to create and save a single infomaterial to the database
     *
     * @param newInfomaterial infomaterial that will be created/saved
     * @param pdf
     * @throws IOException
     */
    @ApiOperation(value = "Create a new infomaterial")
    @PostMapping
    public ResponseEntity<Infomaterial> createInfomaterial(@ModelAttribute Infomaterial newInfomaterial, @RequestPart(value = "file") MultipartFile pdf) throws IOException {
        validator.checkString(newInfomaterial.getName(), "CreateInfomaterialController: createInfomaterial() for name");
        validator.checkString(newInfomaterial.getDescription(), "CreateInfomaterialController: createInfomaterial() for description");
        validator.checkString(newInfomaterial.getUrl(), "CreateInfomaterialController: createInfomaterial() for url");
        validator.checkPdfUpload(pdf, "CreateInfomationController: createInfomaterial()");
        return new ResponseEntity<>(service.saveInfomaterial(newInfomaterial, pdf), HttpStatus.CREATED);
    }

}
