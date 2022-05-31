package rs.sbnz.gunorama;

import org.drools.core.ClockType;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GunoramaApplication {

	private static Logger log = LoggerFactory.getLogger(GunoramaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GunoramaApplication.class, args);
	}

	@Bean
	public KieSession kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("rs.sbnz", "gunorama-rules", "0.0.1-SNAPSHOT"));
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		KieBase kbase = kContainer.newKieBase(kbconf);
//		KieScanner kScanner = ks.newKieScanner(kContainer);
//		kScanner.start(10_000);
		KieSessionConfiguration ksconf1 = ks.newKieSessionConfiguration();
		ksconf1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
		return kbase.newKieSession(ksconf1, null);
	}
}
