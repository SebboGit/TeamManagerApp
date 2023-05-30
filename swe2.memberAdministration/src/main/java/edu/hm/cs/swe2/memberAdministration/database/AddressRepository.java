package edu.hm.cs.swe2.memberAdministration.database;

import edu.hm.cs.swe2.memberAdministration.models.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
