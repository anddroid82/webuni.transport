package hu.webuni.transport.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Section {

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne
	private Milestone fromMilestone;
	
	@OneToOne
	private Milestone toMilestone;
	
	private Integer number;
	
	@ManyToOne
	private TransportPlan transportPlan;

	public Section() {
	}

	public Section(Long id, Milestone fromMilestone, Milestone toMilestone, Integer number, TransportPlan transportPlan) {
		super();
		this.id = id;
		this.fromMilestone = fromMilestone;
		this.toMilestone = toMilestone;
		this.number = number;
		this.transportPlan = transportPlan;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Milestone getFromMilestone() {
		return fromMilestone;
	}

	public void setFromMilestone(Milestone fromMilestone) {
		this.fromMilestone = fromMilestone;
	}

	public Milestone getToMilestone() {
		return toMilestone;
	}

	public void setToMilestone(Milestone toMilestone) {
		this.toMilestone = toMilestone;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public TransportPlan getTransportPlan() {
		return transportPlan;
	}

	public void setTransportPlan(TransportPlan transportPlan) {
		this.transportPlan = transportPlan;
		this.transportPlan.addSection(this);
	}
	
	@Override
	public String toString() {
		return "Section [id=" + id + ", fromMilestone=" + fromMilestone + ", toMilestone=" + toMilestone + ", number="
				+ number + "]";
	}
	
}
