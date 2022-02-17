package hu.webuni.transport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.transport.dto.AddressDto;
import hu.webuni.transport.mapper.AddressMapper;
import hu.webuni.transport.model.Address;
import hu.webuni.transport.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	@Autowired
	AddressMapper addressMapper;
	
	@Autowired
	AddressService addressService;
	
	@GetMapping("/{id}")
	public ResponseEntity<AddressDto> getAddress(@PathVariable Long id) {
		Address address = addressService.getAddress(id);
		if (address != null) {
			return ResponseEntity.ok(addressMapper.addressToDto(address));
		}else{
			return ResponseEntity.notFound().build();
		}
	}
	
}
