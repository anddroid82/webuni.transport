package hu.webuni.transport.dto;

public class DelayDto {

	private Long milestoneId;
	private Long delay;
	
	public DelayDto() {
		super();
	}

	public DelayDto(Long milestoneId, Long delay) {
		super();
		this.milestoneId = milestoneId;
		this.delay = delay;
	}

	public Long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(Long milestoneId) {
		this.milestoneId = milestoneId;
	}

	public Long getDelay() {
		return delay;
	}

	public void setDelay(Long delay) {
		this.delay = delay;
	}

	@Override
	public String toString() {
		return "DelayDto [milestoneId=" + milestoneId + ", delay=" + delay + "]";
	}
	
	
	
}
