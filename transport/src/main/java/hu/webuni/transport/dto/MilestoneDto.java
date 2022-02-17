package hu.webuni.transport.dto;

import java.time.LocalDateTime;

public class MilestoneDto {

	private Long id;
	
	private AddressDto address;
	
	private LocalDateTime plannedTime;
	
	private SectionDto fromSection;
	
	private SectionDto toSection;

	public MilestoneDto() {
	}

	public MilestoneDto(Long id, AddressDto address, LocalDateTime plannedTime, SectionDto fromSection, SectionDto toSection) {
		this.id = id;
		this.address = address;
		this.plannedTime = plannedTime;
		this.fromSection = fromSection;
		this.toSection = toSection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}

	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}

	public SectionDto getFromSection() {
		return fromSection;
	}

	public void setFromSection(SectionDto fromSection) {
		this.fromSection = fromSection;
		//this.fromSection.setFromMilestone(this);
	}

	public SectionDto getToSection() {
		return toSection;
	}

	public void setToSection(SectionDto toSection) {
		this.toSection = toSection;
		//this.toSection.setToMilestone(this);
	}
	
	@Override
	public String toString() {
		return "Milestone [id=" + id + ", address=" + address + ", plannedTime=" + plannedTime + "]";
	}
	
	
}
