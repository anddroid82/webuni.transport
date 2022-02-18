package hu.webuni.transport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hu.webuni.transport.dto.TransportPlanDto;
import hu.webuni.transport.mapper.TransportPlanMapper;
import hu.webuni.transport.model.TransportPlan;
import hu.webuni.transport.service.TransportPlanService;

@RestController
@RequestMapping("/api/transportPlans")
public class TransportPlanController {

	@Autowired
	TransportPlanService transportPlanService;
	
	@Autowired
	TransportPlanMapper transportPlanMapper;
	
	@GetMapping("/{id}")
	public ResponseEntity<TransportPlanDto> getTransportPlan(@PathVariable Long id) {
		TransportPlan tp = transportPlanService.getTransportPlan(id);
		if (tp != null) {
			return ResponseEntity.ok(transportPlanMapper.transportPlanToDto(tp));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
