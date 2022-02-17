package hu.webuni.transport.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class TransportPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transportplan_generator")
	@SequenceGenerator(name="transportplan_generator", sequenceName = "mtransportplan_seq")
	private Long id;
	
	private Float income;
	
	@OneToMany(mappedBy = "transportPlan")
	private List<Section> sections;

	public TransportPlan() {
	}

	public TransportPlan(Long id, Float income, List<Section> sections) {
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

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	@Override
	public String toString() {
		return "TransportPlan [id=" + id + ", sections=" + sections + "]";
	}
	
	public void addSection(Section section) {
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
