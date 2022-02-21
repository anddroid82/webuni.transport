package hu.webuni.transport;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;
import hu.webuni.transport.config.TransportConfigurationProperties;
import hu.webuni.transport.dto.DelayDto;
import hu.webuni.transport.dto.LoginDto;
import hu.webuni.transport.dto.MilestoneDto;
import hu.webuni.transport.dto.TransportPlanDto;
import hu.webuni.transport.service.MilestoneService;
import hu.webuni.transport.service.TransportPlanService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class TransportPlanControllerIT {

	private static final String BASE_URI = "/api/transportPlans";
	
	private static final String BASE_URI_LOGIN = "/api/login";

	private static final String username = "user";
	private static final String userpassword = "password";

	private static final String adminname = "admin";
	private static final String adminpassword = "password";

	@Autowired
	TransportConfigurationProperties transportConfigurationProperties;
	
	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	TransportPlanService transportPlanService;
	
	@Autowired
	MilestoneService milestoneService;
	
	private String jwt;
	
	public void setJwt(String name, String password) {
		this.jwt = webTestClient
			.post()
			.uri(BASE_URI_LOGIN)
			.bodyValue(new LoginDto(name, password))
			.exchange()
			.expectBody(String.class)
			.returnResult()
			.getResponseBody();
	}
	
	@Test
	void testFail1ByWrongAdminRole() throws Exception {
		setJwt(username, userpassword);
		Long tpId=1L;
		Long mileStoneId=5L;
		Long delay = 45L;
		webTestClient
			.post()
			.uri(BASE_URI+"/"+tpId+"/delay")
			.headers(headers -> headers.setBearerAuth(jwt))
			.bodyValue(new DelayDto(mileStoneId,delay))
			.exchange()
			.expectStatus().isForbidden();
	}
	
	@Test
	void testFail2ByNonExistentTransportPlanId() throws Exception {
		setJwt(adminname, adminpassword);
		Long tpId=3L;
		Long mileStoneId=5L;
		Long delay = 45L;
		webTestClient
			.post()
			.uri(BASE_URI+"/"+tpId+"/delay")
			.headers(headers -> headers.setBearerAuth(jwt))
			.bodyValue(new DelayDto(mileStoneId,delay))
			.exchange()
			.expectStatus().isNotFound();
	}
	
	@Test
	void testFail3ByNonExistentMilestoneId() throws Exception {
		setJwt(adminname, adminpassword);
		Long tpId=1L;
		Long mileStoneId=50L;
		Long delay = 45L;
		webTestClient
			.post()
			.uri(BASE_URI+"/"+tpId+"/delay")
			.headers(headers -> headers.setBearerAuth(jwt))
			.bodyValue(new DelayDto(mileStoneId,delay))
			.exchange()
			.expectStatus().isNotFound();
	}
	
	@Test
	void testFail4ByTransportPlanNotContainsMilestoneId() throws Exception {
		setJwt(adminname, adminpassword);
		Long tpId=1L;
		Long mileStoneId=14L;
		Long delay = 45L;
		webTestClient
			.post()
			.uri(BASE_URI+"/"+tpId+"/delay")
			.headers(headers -> headers.setBearerAuth(jwt))
			.bodyValue(new DelayDto(mileStoneId,delay))
			.exchange()
			.expectStatus().isBadRequest();
	}
	
	@Test
	void testSuccess1ByFromMilestoneInTransportPlan() throws Exception {
		setJwt(adminname, adminpassword);
		Long tpId=1L;
		Long fromMileStoneId=3L;
		int sectionNumber = 1;
		
		Long delay = 45L;
		Long incomeMinusWithPercent = 2L;
		
		TransportPlanDto transportPlanBefore = webTestClient
				.get()
				.uri(BASE_URI+"/"+tpId)
				.headers(headers -> headers.setBearerAuth(jwt))
				.exchange()
				.expectBody(TransportPlanDto.class)
				.returnResult()
				.getResponseBody();
		
		TransportPlanDto transportPlanAfter = webTestClient
			.post()
			.uri(BASE_URI+"/"+tpId+"/delay")
			.headers(headers -> headers.setBearerAuth(jwt))
			.bodyValue(new DelayDto(fromMileStoneId,delay))
			.exchange()
			.expectBody(TransportPlanDto.class)
			.returnResult()
			.getResponseBody();
		
		MilestoneDto fromMileStoneBefore = transportPlanBefore.getSections().get(sectionNumber).getFromMilestone();
		MilestoneDto fromMileStoneAfter = transportPlanAfter.getSections().get(sectionNumber).getFromMilestone();
		MilestoneDto toMileStoneBefore = transportPlanBefore.getSections().get(sectionNumber).getToMilestone();
		MilestoneDto toMileStoneAfter = transportPlanAfter.getSections().get(sectionNumber).getToMilestone();
		
		//a fromMilestone idő ellenőrzése
		assertThat(fromMileStoneBefore.getPlannedTime().plusMinutes(delay)).isEqualToIgnoringSeconds(fromMileStoneAfter.getPlannedTime());
		//a toMilestone idő ellenőrzése
		assertThat(toMileStoneBefore.getPlannedTime().plusMinutes(delay)).isEqualToIgnoringSeconds(toMileStoneAfter.getPlannedTime());
		//a bevétel ellenőrzése
		assertThat(transportPlanBefore.getIncome()*(100-incomeMinusWithPercent)/100.0f).isEqualTo(transportPlanAfter.getIncome());
		
	}
	
	@Test
	void testSuccess2ByToMilestoneInTransportPlan() throws Exception {
		setJwt(adminname, adminpassword);
		Long tpId=1L;
		Long fromMileStoneId=5L;
		int sectionNumber = 1;
		
		Long delay = 80L;
		Long incomeMinusWithPercent = 5L;
		
		TransportPlanDto transportPlanBefore = webTestClient
				.get()
				.uri(BASE_URI+"/"+tpId)
				.headers(headers -> headers.setBearerAuth(jwt))
				.exchange()
				.expectBody(TransportPlanDto.class)
				.returnResult()
				.getResponseBody();
		
		TransportPlanDto transportPlanAfter = webTestClient
			.post()
			.uri(BASE_URI+"/"+tpId+"/delay")
			.headers(headers -> headers.setBearerAuth(jwt))
			.bodyValue(new DelayDto(fromMileStoneId,delay))
			.exchange()
			.expectBody(TransportPlanDto.class)
			.returnResult()
			.getResponseBody();
		
		MilestoneDto toMileStoneBefore = transportPlanBefore.getSections().get(sectionNumber).getToMilestone();
		MilestoneDto toMileStoneAfter = transportPlanAfter.getSections().get(sectionNumber).getToMilestone();
		MilestoneDto fromMileStoneBefore = transportPlanBefore.getSections().get(sectionNumber+1).getFromMilestone();
		MilestoneDto fromMileStoneAfter = transportPlanAfter.getSections().get(sectionNumber+1).getFromMilestone();
		
		//a fromMilestone idő ellenőrzése
		assertThat(toMileStoneBefore.getPlannedTime().plusMinutes(delay)).isEqualToIgnoringSeconds(toMileStoneAfter.getPlannedTime());
		//a toMilestone idő ellenőrzése
		assertThat(fromMileStoneBefore.getPlannedTime().plusMinutes(delay)).isEqualToIgnoringSeconds(fromMileStoneAfter.getPlannedTime());
		//a bevétel ellenőrzése
		assertThat(transportPlanBefore.getIncome()*(100-incomeMinusWithPercent)/100.0f).isEqualTo(transportPlanAfter.getIncome());
		
	}

}
