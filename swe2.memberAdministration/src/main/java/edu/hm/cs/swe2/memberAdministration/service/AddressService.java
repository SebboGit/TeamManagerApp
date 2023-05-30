package edu.hm.cs.swe2.memberAdministration.service;

import edu.hm.cs.swe2.memberAdministration.database.AddressRepository;
import edu.hm.cs.swe2.memberAdministration.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepo;

    public Integer createAddress(Address address) {
        Address newAddress = addressRepo.save(address);
        return newAddress.getId();
    }

    public Iterable<Address> getAllAddress() {
        return addressRepo.findAll();
    }

}
