package hu.webuni.transport.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.transport.model.Milestone;
import hu.webuni.transport.repository.MilestoneRepository;

@Service
public class MilestoneService {

	@Autowired
	MilestoneRepository milestoneRepository;
	
	@Transactional
	public Milestone getMilestone(Long id) {
		Optional<Milestone> milestone = milestoneRepository.findByIdFull(id);
		if (milestone.isPresent()) {
			return milestone.get();
		}
		return null;
	}
	
}
