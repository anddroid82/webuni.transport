package hu.webuni.transport.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@NamedEntityGraph(
		name = "Milestone.full",
		attributeNodes = {
				@NamedAttributeNode("address"),
				@NamedAttributeNode(value = "fromSection", subgraph = "Section.full"),
				@NamedAttributeNode(value = "toSection", subgraph = "Section.full"),
		},
		subgraphs = {
				@NamedSubgraph(
						name = "Section.full",
						attributeNodes = {
								@NamedAttributeNode(value = "fromMilestone"), //TODO: kell-e????
								@NamedAttributeNode(value = "toMilestone"), //TODO: kell-e????
								@NamedAttributeNode(value = "transportPlan")
						}
				)
		}
)
public class Milestone {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "milestone_generator")
	@SequenceGenerator(name="milestone_generator", sequenceName = "milestone_seq")
	private Long id;
	
	@ManyToOne
	private Address address;
	
	private LocalDateTime plannedTime;
	
	@OneToOne(mappedBy = "fromMilestone")
	private Section fromSection;
	
	@OneToOne(mappedBy = "toMilestone")
	private Section toSection;

	public Milestone() {
	}

	public Milestone(Long id, Address address, LocalDateTime plannedTime, Section fromSection, Section toSection) {
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}

	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}

	public Section getFromSection() {
		return fromSection;
	}

	public void setFromSection(Section fromSection) {
		this.fromSection = fromSection;
		//this.fromSection.setFromMilestone(this);
	}

	public Section getToSection() {
		return toSection;
	}

	public void setToSection(Section toSection) {
		this.toSection = toSection;
		//this.toSection.setToMilestone(this);
	}
	
	@Override
	public String toString() {
		return "Milestone [id=" + id + ", address=" + address + ", plannedTime=" + plannedTime + "]";
	}
	
	
}
