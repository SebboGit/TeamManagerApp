package edu.hm.cs.swe2.memberAdministration.controllers;

import edu.hm.cs.swe2.memberAdministration.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import edu.hm.cs.swe2.memberAdministration.models.Address;

@RestController
@RequestMapping("memberAdministration")
@CrossOrigin(origins = "http://localhost:3000")

public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/createAddress", method = RequestMethod.POST)
    public Integer createAddress(@RequestBody Address address) {
        return addressService.createAddress(address);
    }
}
