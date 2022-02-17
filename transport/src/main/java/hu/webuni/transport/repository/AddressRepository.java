package hu.webuni.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import hu.webuni.transport.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>, PagingAndSortingRepository<Address, Long>, JpaSpecificationExecutor<Address> {
	
}
