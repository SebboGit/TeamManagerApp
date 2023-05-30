package edu.hm.cs.swe2.teamManager.controller.infomaterial;


import edu.hm.cs.swe2.teamManager.controller.InputValidator;
import edu.hm.cs.swe2.teamManager.models.Infomaterial;
import edu.hm.cs.swe2.teamManager.services.infomaterial.EditInfomaterialService;
import edu.hm.cs.swe2.teamManager.services.infomaterial.ReadInfomaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Rest Controller used for editing different properties of a infomaterial
 *
 * @author Michael Fortenbacher
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Endpoint for editing a infomaterial", tags = {"Edit a infomaterial"})
@RequestMapping(path = "api/v1.0/infomaterial")
public class EditInfomaterialController {

    @Autowired
    private EditInfomaterialService service;

    @Autowired
    private ReadInfomaterialService readService;

    @Autowired
    private InputValidator validator;

    @ApiOperation(value = "Edit the name of a infomaterial")
    @PutMapping("infoName")
    public ResponseEntity<Infomaterial> editInfoName(Integer infoId, String newName) {
        return new ResponseEntity<>(this.service.editInfoName(infoId, newName), HttpStatus.OK);
    }

    @ApiOperation(value = "Edit the description of a infomaterial")
    @PutMapping("infoDescription")
    public ResponseEntity<Infomaterial> editInfoDescription(Integer infoId, String newDescription) {
        return new ResponseEntity<>(this.service.editInfoDescription(infoId, newDescription), HttpStatus.OK);
    }

    @ApiOperation(value = "Edit the url of a infomaterial")
    @PutMapping("infoUrl")
    public ResponseEntity<Infomaterial> editInfoUrl(Integer infoId, String newUrl) {
        return new ResponseEntity<>(this.service.editInfoUrl(infoId, newUrl), HttpStatus.OK);
    }

    @ApiOperation(value = "Edit the pdf of a infomaterial-entity")
    @PutMapping("infoPdf")
    public ResponseEntity<Infomaterial> editInfoPdf(Integer infoId, @RequestPart(value = "PDF") MultipartFile pdf) throws IOException {
        return new ResponseEntity<>(this.service.editInfoPdf(infoId, pdf.getBytes()), HttpStatus.OK);
    }

    @PutMapping("editInfomaterial")
    public ResponseEntity<Infomaterial> editInfomaterial(@ModelAttribute Infomaterial newInfomaterial, @RequestPart(value = "file") MultipartFile pdf) throws IOException {
        validator.checkId(newInfomaterial.getId(), "EditInfomaterialController: editInfomaterial()");
        int infoId = newInfomaterial.getId();
        validator.checkString(newInfomaterial.getName(), "EditInfomaterialController: editInfomaterial() for name");
        validator.checkString(newInfomaterial.getDescription(), "EditInfomaterialController: editInfomaterial() for description");
        validator.checkString(newInfomaterial.getUrl(), "EditInfomaterialController: editInfomaterial() for url");
        validator.checkPdfUpload(pdf, "EditInfomaterialController: editInfomaterial()");
        editInfoName(infoId, newInfomaterial.getName());
        editInfoDescription(infoId, newInfomaterial.getDescription());
        editInfoUrl(infoId, newInfomaterial.getUrl());
        editInfoPdf(infoId, pdf);
        //response is still the old infomaterial at the moment
        return new ResponseEntity<>(this.readService.findInfoById(infoId), HttpStatus.OK);
    }
}
