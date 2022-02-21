package hu.webuni.transport.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping
	public ResponseEntity<AddressDto> createAddress(@Valid @RequestBody(required = true) AddressDto address,BindingResult bindingResult) {
		if (bindingResult.hasErrors() || address.getId()!=null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}else {
			Address addr = addressMapper.dtoToAddress(address);
			return ResponseEntity.ok(addressMapper.addressToDto(addressService.createAddress(addr)));
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AddressDto> getAddress(@PathVariable Long id) {
		Address address = addressService.getAddress(id);
		if (address != null) {
			return ResponseEntity.ok(addressMapper.addressToDto(address));
		}else{
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<AddressDto>> getAllAddress() {
		List<Address> allAddress = addressService.getAllAddress();
		return ResponseEntity.ok(addressMapper.addressesToDtos(allAddress));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<AddressDto> deleteAddress(@PathVariable Long id) {
		try {
			addressService.deleteAddress(id);
		}catch (Exception e) {}
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AddressDto> modifyAddress(@Valid @RequestBody(required = true) AddressDto address, BindingResult bindingResult, @PathVariable Long id) {
		if (bindingResult.hasErrors() || (address.getId()!= null && address.getId() != id))  {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}else {
			address.setId(id);
			Address addr = addressService.modifyAddress(addressMapper.dtoToAddress(address));
			if (addr == null) {
				return ResponseEntity.notFound().build();
			}else {
				return ResponseEntity.ok(addressMapper.addressToDto(addr));
			}
		}
	}
	
	@PostMapping("/search")
	public ResponseEntity<List<AddressDto>> search(@RequestBody(required = true) AddressDto address, @SortDefault("id") Pageable pageable) {
		Page<Address> page = addressService.searchAddress(addressMapper.dtoToAddress(address), pageable);
		if (page.hasContent()) {
			HttpHeaders header = new HttpHeaders();
			header.set("X-Total-Count", String.valueOf(page.getTotalElements()));
			return ResponseEntity.ok().headers(header).body(addressMapper.addressesToDtos(page.getContent())); 
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
