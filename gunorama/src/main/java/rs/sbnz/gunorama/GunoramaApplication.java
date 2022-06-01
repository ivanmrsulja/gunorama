package rs.sbnz.gunorama;

import org.drools.core.ClockType;
import org.drools.core.marshalling.impl.ProtobufMessages;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.sbnz.gunorama.service.TemplateService;

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
		KieBaseConfiguration kieBaseConfiguration = ks.newKieBaseConfiguration();
		kieBaseConfiguration.setOption(EventProcessingOption.STREAM);
		//kbconf.setOption(EqualityBehaviorOption.EQUALITY);
		KieBase kieBase = kContainer.newKieBase(kieBaseConfiguration);

//		String drl = TemplateService.compileOdredjivanjeKalibaraTemplate();
//		KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
//		knowledgeBuilder.add(ResourceFactory.newByteArrayResource(drl.getBytes()), ResourceType.DRL);



		KieSessionConfiguration kieSessionConfiguration = ks.newKieSessionConfiguration();
		kieSessionConfiguration.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
		return kieBase.newKieSession(kieSessionConfiguration, null);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
