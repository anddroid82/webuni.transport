package hu.webuni.transport.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.transport.model.Address;
import hu.webuni.transport.model.Milestone;
import hu.webuni.transport.model.Section;
import hu.webuni.transport.model.TransportPlan;
import hu.webuni.transport.repository.AddressRepository;
import hu.webuni.transport.repository.MilestoneRepository;
import hu.webuni.transport.repository.SectionRepository;
import hu.webuni.transport.repository.TransportPlanRepository;

@Service
public class InitDbService {

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	MilestoneRepository milestoneRepository;
	
	@Autowired
	SectionRepository sectionRepository;
	
	@Autowired
	TransportPlanRepository transportPlanRepository;
	
	@Transactional
	public void clearData() {
		sectionRepository.deleteAll();
		transportPlanRepository.deleteAll();
		milestoneRepository.deleteAll();
		addressRepository.deleteAll();
	}
	
	@Transactional
	public void addInitData() {
		Address a1 = new Address(0L, "HU", "Eger", "Mátyás király", 3300, "9", 33.9f, 40.5f);
		Address a2 = new Address(0L, "HU", "Szeged", "Kovács István", 5400, "12", 53.9f, 80.2f);
		Address a3 = new Address(0L, "HU", "Pécs", "Pető Gábor", 2723, "25", 43.9f, 67.2f);
		Address a4 = new Address(0L, "HU", "Budapest", "Szokoli János", 1200, "11", 44.9f, 56.2f);
		Address a5 = new Address(0L, "HU", "Miskolc", "István király", 3890, "56", 41.9f, 58.2f);
		Address a6 = new Address(0L, "HU", "Debrecen", "Böszörményi", 6830, "1", 46.9f, 68.2f);
		
		a1=addressRepository.save(a1);a2=addressRepository.save(a2);a3=addressRepository.save(a3);
		a4=addressRepository.save(a4);a5=addressRepository.save(a5);a6=addressRepository.save(a6);
		
		Milestone m1=new Milestone(0L, a1, LocalDateTime.parse("2022-03-03T12:23:30"), null, null);
		Milestone m2=new Milestone(0L, a2, LocalDateTime.parse("2022-03-04T13:23:30"), null, null);
		Milestone m3=new Milestone(0L, a3, LocalDateTime.parse("2022-03-06T15:23:30"), null, null);
		Milestone m4=new Milestone(0L, a4, LocalDateTime.parse("2022-03-07T16:26:30"), null, null);
		Milestone m5=new Milestone(0L, a1, LocalDateTime.parse("2022-03-08T17:27:30"), null, null);
		Milestone m6=new Milestone(0L, a4, LocalDateTime.parse("2022-03-10T12:23:30"), null, null);
		Milestone m7=new Milestone(0L, a5, LocalDateTime.parse("2022-03-11T08:23:30"), null, null);
		Milestone m8=new Milestone(0L, a6, LocalDateTime.parse("2022-03-12T16:23:30"), null, null);
		Milestone m9=new Milestone(0L, a2, LocalDateTime.parse("2022-03-13T18:23:30"), null, null);
		
		m1 = milestoneRepository.save(m1);m2 = milestoneRepository.save(m2);m3 = milestoneRepository.save(m3);
		m4 = milestoneRepository.save(m4);m5 = milestoneRepository.save(m5);m6 = milestoneRepository.save(m6);
		m7 = milestoneRepository.save(m7);m8 = milestoneRepository.save(m8);m9 = milestoneRepository.save(m9);
		
		TransportPlan tp1 = new TransportPlan(0L, 560000f, null);
		TransportPlan tp2 = new TransportPlan(0L, 870000f, null);
		
		tp1 = transportPlanRepository.save(tp1);
		tp2 = transportPlanRepository.save(tp2);
		
		Section s1 = new Section(0L, m1, m2, 0, tp1);
		Section s2 = new Section(0L, m2, m3, 1, tp1);
		Section s3 = new Section(0L, m3, m4, 2, tp1);
		Section s4 = new Section(0L, m4, m5, 3, tp1);
		
		Section s5 = new Section(0L, m6, m7, 0, tp2);
		Section s6 = new Section(0L, m7, m8, 1, tp2);
		Section s7 = new Section(0L, m8, m9, 2, tp2);
		
		s1 = sectionRepository.save(s1);s2 = sectionRepository.save(s2);s3 = sectionRepository.save(s3);
		s4 = sectionRepository.save(s4);s5 = sectionRepository.save(s5);s6 = sectionRepository.save(s6);
		s7 = sectionRepository.save(s7);
		
		
	}
}
