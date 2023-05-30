package edu.hm.cs.swe2.teamManager.controller.infomaterial;

import edu.hm.cs.swe2.teamManager.controller.InputValidator;
import edu.hm.cs.swe2.teamManager.models.Infomaterial;
import edu.hm.cs.swe2.teamManager.services.infomaterial.ReadInfomaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Controller that is used when reading one or infomaterials from the database.
 *
 * @author Michael Fortenbacher
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Endpoint for reading infomaterials from the database", tags = {"Get a infomaterial"})
@RequestMapping(path = "api/v1.0/infomaterial")
public class ReadInfomaterialController {
    @Autowired
    private ReadInfomaterialService service;

    @Autowired
    private InputValidator validator;

    @ApiOperation(value = "Get a infomaterial by its Id")
    @GetMapping(value = "{infoId}")
    public Infomaterial getInfoById(@PathVariable("infoId") Integer infoId) {
        validator.checkId(infoId, "ReadInfomaterialController: getInfobyId()");
        return this.service.findInfoById(infoId);
    }

    @ApiOperation(value = "Get all infomaterial ")
    @GetMapping
    public List<Infomaterial> getAllInfomaterial() {
        return this.service.findAllInfo();
    }
}
