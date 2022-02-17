package hu.webuni.transport.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.transport.model.Address;
import hu.webuni.transport.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	public Address getAddress(Long id) {
		Optional<Address> addressOpt = addressRepository.findById(id);
		if (addressOpt.isPresent()) {
			return addressOpt.get();
		}
		return null;
	}
	
}
