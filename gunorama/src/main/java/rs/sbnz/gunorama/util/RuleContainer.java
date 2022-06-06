package rs.sbnz.gunorama.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.sbnz.gunorama.service.RuleService;

import java.util.List;

@Component
@Getter
@Setter
public class RuleContainer {

    private final RuleService ruleService;

    private List<String> rules;

    @Autowired
    public RuleContainer(RuleService ruleService) {
        this.ruleService = ruleService;
        this.rules = this.ruleService.readRules("KonkretnaNamjena", "PreporukaOruzja");
    }
}
