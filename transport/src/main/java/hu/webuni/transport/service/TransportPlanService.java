package hu.webuni.transport.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.transport.config.TransportConfigurationProperties;
import hu.webuni.transport.model.Milestone;
import hu.webuni.transport.model.Section;
import hu.webuni.transport.model.TransportPlan;
import hu.webuni.transport.repository.TransportPlanRepository;

@Service
public class TransportPlanService {

	@Autowired
	TransportPlanRepository transportPlanRepository;
	
	@Autowired
	MilestoneService milestoneService;
	
	@Autowired
	TransportConfigurationProperties transportConfigurationProperties;
	
	public TransportPlan getTransportPlan(Long id) {
		Optional<TransportPlan> tp = transportPlanRepository.findByIdFull(id);
		if (tp.isPresent()) {
			TransportPlan transportPlan = tp.get();
			transportPlan.getSections().sort(SectionComparators.createComparatorBySectionNumber());
			return transportPlan;
		}
		return null;
	}

	@Transactional
	public TransportPlan delayTransportPlan(Long transportPlanId, Long milestoneId, Long delayMins) {
		Milestone milestone = milestoneService.getMilestone(milestoneId);
		//létező terv-e, illetve van-e ilyen mérföldkő?
		if (!transportPlanRepository.existsById(transportPlanId) || milestone == null) {
			throw new NotFoundException();
		}
		//ha a tervnek nem része a mérföldkő
		if ((milestone.getFromSection() != null && milestone.getFromSection().getTransportPlan().getId() != transportPlanId) ||
				(milestone.getToSection() != null && milestone.getToSection().getTransportPlan().getId() != transportPlanId) || 
				(milestone.getFromSection() == null && milestone.getToSection() == null) ) {
			 throw new BadRequestException();
		}
		TransportPlan transportPlan = getTransportPlan(transportPlanId);
		//a mérföldkő idejét növeljük
		milestone.setPlannedTime(milestone.getPlannedTime().plusMinutes(delayMins));
		//ha kezdő mérföldkő
		if (milestone.getFromSection() != null) {
			Milestone toMilestone = milestone.getFromSection().getToMilestone();
			toMilestone.setPlannedTime(toMilestone.getPlannedTime().plusMinutes(delayMins));
		}
		//ha végmérföldkő
		if (milestone.getToSection() != null) {	
			int number = milestone.getToSection().getNumber();
			if (number+1 < transportPlan.getSections().size()) {
				Section nextSection = transportPlan.getSections().get(number+1);
				nextSection.getFromMilestone().setPlannedTime(nextSection.getFromMilestone().getPlannedTime().plusMinutes(delayMins));
			}
		}
		//a bevétel csökkentése
		int[] delaysections = transportConfigurationProperties.getDelaysections();
		int[] delaypercents = transportConfigurationProperties.getDelaypercents();
		for (int i=0;i<delaysections.length;i++) {
			if (delayMins >= delaysections[i]) {
				float newIncome=transportPlan.getIncome()*((100-delaypercents[i])/100.0f);
				transportPlan.setIncome(newIncome);
				break;
			}
		}
		
		return transportPlan;
	}
}
