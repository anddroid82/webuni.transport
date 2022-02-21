package hu.webuni.transport.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.webuni.transport.dto.MilestoneDto;
import hu.webuni.transport.dto.SectionDto;
import hu.webuni.transport.dto.TransportPlanDto;
import hu.webuni.transport.model.Milestone;
import hu.webuni.transport.model.Section;
import hu.webuni.transport.model.TransportPlan;

@Mapper(componentModel = "spring")
public interface TransportPlanMapper {

	@Mapping(target = "sections", qualifiedByName = "sectionToDto")
	TransportPlanDto transportPlanToDto(TransportPlan tp);
	
	@Named("sectionToDto")
	@Mapping(target = "fromMilestone", qualifiedByName = "milestoneToDto")
	@Mapping(target = "toMilestone", qualifiedByName = "milestoneToDto")
	@Mapping(target = "transportPlan", ignore = true)
	SectionDto sectionToDto(Section section);
	
	@Named("milestoneToDto")
	@Mapping(target = "fromSection", ignore = true)
	@Mapping(target = "toSection", ignore = true)
	MilestoneDto milestoneToDto(Milestone milestone);
	
	//#################################
	
	/*
	@Mapping(target = "sections", qualifiedByName = "dtoToSection")
	TransportPlan dtoToTransportPlan(TransportPlanDto tp);
	
	@Named("dtoToSection")
	@Mapping(target = "fromMilestone", qualifiedByName = "dtoToMilestone")
	@Mapping(target = "toMilestone", qualifiedByName = "dtoToMilestone")
	@Mapping(target = "transportPlan", ignore = true)
	Section dtoToSection(SectionDto section);
	
	@Named("dtoToMilestone")
	@Mapping(target = "fromSection", ignore = true)
	@Mapping(target = "toSection", ignore = true)
	Milestone dtoToMilestone(MilestoneDto milestone);
	*/
}
