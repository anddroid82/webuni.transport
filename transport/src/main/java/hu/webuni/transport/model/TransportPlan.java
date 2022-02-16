package hu.webuni.transport.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TransportPlan {

	@Id
	@GeneratedValue
	private Long id;
	
	private Float income;
	
	@OneToMany(mappedBy = "transportPlan")
	private List<Section> sections;

	public TransportPlan() {
	}

	public TransportPlan(Long id, List<Section> sections) {
		this.id = id;
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
		this.sections.add(section);
	}
	
}
