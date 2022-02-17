package hu.webuni.transport.service;

import org.springframework.data.jpa.domain.Specification;
import hu.webuni.transport.model.Address;
import hu.webuni.transport.model.Address_;

public class AddressSpecifications {

	public static Specification<Address> hasIso(String iso) {
		return (root, cq, cb) -> cb.equal(root.get(Address_.iso), iso);
	}

	public static Specification<Address> hasZip(Integer zip) {
		return (root, cq, cb) -> cb.equal(root.get(Address_.zip), zip);
	}
	
	public static Specification<Address> hasCity(String city) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.city)), city.toLowerCase()+"%");
	}

	public static Specification<Address> hasStreet(String street) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.street)), street.toLowerCase()+"%");
	}
	
}
