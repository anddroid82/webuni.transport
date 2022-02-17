package hu.webuni.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.transport.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	
	
}
