package hu.webuni.transport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import hu.webuni.transport.model.Address;
import hu.webuni.transport.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;

	public Address createAddress(Address address) {
		return addressRepository.save(address);
	}

	public Address getAddress(Long id) {
		Optional<Address> addressOpt = addressRepository.findById(id);
		if (addressOpt.isPresent()) {
			return addressOpt.get();
		}
		return null;
	}

	public List<Address> getAllAddress() {
		return addressRepository.findAll();
	}

	public void deleteAddress(Long id) {
		addressRepository.deleteById(id);
	}

	public Address modifyAddress(Address address) {
		if (addressRepository.existsById(address.getId())) {
			return addressRepository.save(address);
		}
		return null;
	}

	public Page<Address> searchAddress(Address address, Pageable pageable) {
		Specification<Address> spec = Specification.where(null);

		if (address.getIso() != null) {
			spec = spec.and(AddressSpecifications.hasIso(address.getIso()));
		}
		if (address.getZip() != null && address.getZip() != 0) {
			spec = spec.and(AddressSpecifications.hasZip(address.getZip()));
		}
		if (StringUtils.hasText(address.getCity())) {
			spec = spec.and(AddressSpecifications.hasCity(address.getCity()));
		}
		if (StringUtils.hasText(address.getStreet())) {
			spec = spec.and(AddressSpecifications.hasStreet(address.getStreet()));
		}
		return addressRepository.findAll(spec, pageable);
	}
}
