package edu.hm.cs.swe2.teamManager.controller.infomaterial;


import edu.hm.cs.swe2.teamManager.controller.InputValidator;
import edu.hm.cs.swe2.teamManager.services.infomaterial.DeleteInfomaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

/**
 * Rest Controller that is used when one or multiple infomaterials should be deleted from the database
 *
 * @author Michael Fortenbacher
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Endpoint for deleting infomaterials", tags = {"Delete infomaterials"})
@RequestMapping(path = "api/v1.0/infomaterial")
public class DeleteInfomaterialController {

    @Autowired
    private DeleteInfomaterialService service;


    @Autowired
    private InputValidator validator;

    /**
     * Endpoint for deleting a infomaterial with a given id
     *
     * @param infoId Id of the infomaterial
     * @throws NoSuchElementException if the Id doesn't exist in the database
     */
    @ApiOperation(value = "delete a infomaterial")
    @DeleteMapping(value = "{infoId}")
    public ResponseEntity<String> deleteInfomaterial(@PathVariable("infoId") Integer infoId) {
        validator.checkId(infoId, "DeleteInformaterialController: deleteInfomaterial()");
        try {
            this.service.deleteInfomaterial(infoId);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException();
        }
        return new ResponseEntity<>(String.format("Infomaterial with the Id '%d' has been deleted.", infoId), HttpStatus.OK);
    }

    /**
     * Endpoint for deleting all infomaterials in the database
     */
    @ApiOperation(value = "delete all infomaterials")
    @DeleteMapping
    public ResponseEntity<String> deleteAllInfomaterial() {
        this.service.deleteAllInfomaterials();
        return new ResponseEntity<>(String.format("All infomaterials have been deleted."), HttpStatus.OK);
    }
}
