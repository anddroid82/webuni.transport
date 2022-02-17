package hu.webuni.transport.dto;

import java.util.List;

public class TransportPlanDto {

	private Long id;
	
	private Float income;
	
	private List<SectionDto> sections;

	public TransportPlanDto() {
	}

	public TransportPlanDto(Long id, Float income, List<SectionDto> sections) {
		this.id = id;
		this.income = income;
		this.sections = sections;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SectionDto> getSections() {
		return sections;
	}

	public void setSections(List<SectionDto> sections) {
		this.sections = sections;
	}

	@Override
	public String toString() {
		return "TransportPlan [id=" + id + ", sections=" + sections + "]";
	}
	
	public void addSection(SectionDto section) {
		section.setTransportPlan(this);
		this.sections.add(section);
	}

	public Float getIncome() {
		return income;
	}

	public void setIncome(Float income) {
		this.income = income;
	}
	
	
}
