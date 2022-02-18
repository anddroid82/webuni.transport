package hu.webuni.transport.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.transport.model.TransportPlan;
import hu.webuni.transport.repository.TransportPlanRepository;

@Service
public class TransportPlanService {

	@Autowired
	TransportPlanRepository transportPlanRepository;
	
	public TransportPlan getTransportPlan(Long id) {
		Optional<TransportPlan> tp = transportPlanRepository.findByIdFull(id);
		if (tp.isPresent()) {
			return tp.get();
		}
		return null;
	}
	
}
