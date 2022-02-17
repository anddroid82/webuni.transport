package hu.webuni.transport.dto;

public class SectionDto {

	private Long id;
	
	private MilestoneDto fromMilestone;
	
	private MilestoneDto toMilestone;
	
	private Integer number;
	
	private TransportPlanDto transportPlan;

	public SectionDto() {
	}

	public SectionDto(Long id, MilestoneDto fromMilestone, MilestoneDto toMilestone, Integer number, TransportPlanDto transportPlan) {
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

	public MilestoneDto getFromMilestone() {
		return fromMilestone;
	}

	public void setFromMilestone(MilestoneDto fromMilestone) {
		this.fromMilestone = fromMilestone;
		this.fromMilestone.setFromSection(this);
	}

	public MilestoneDto getToMilestone() {
		return toMilestone;
	}

	public void setToMilestone(MilestoneDto toMilestone) {
		this.toMilestone = toMilestone;
		this.toMilestone.setToSection(this);
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public TransportPlanDto getTransportPlan() {
		return transportPlan;
	}

	public void setTransportPlan(TransportPlanDto transportPlan) {
		this.transportPlan = transportPlan;
		//this.transportPlan.addSection(this);
	}
	
	@Override
	public String toString() {
		return "Section [id=" + id + ", fromMilestone=" + fromMilestone + ", toMilestone=" + toMilestone + ", number="
				+ number + "]";
	}
	
}
