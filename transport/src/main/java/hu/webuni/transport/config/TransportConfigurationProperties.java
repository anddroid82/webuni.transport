package hu.webuni.transport.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "transport")
public class TransportConfigurationProperties {

	int[] delaysections;
	int[] delaypercents;
	
	public int[] getDelaysections() {
		return delaysections;
	}
	public void setDelaysections(int[] delaysections) {
		this.delaysections = delaysections;
	}
	public int[] getDelaypercents() {
		return delaypercents;
	}
	public void setDelaypercents(int[] delaypercents) {
		this.delaypercents = delaypercents;
	}
	
	
	
}
