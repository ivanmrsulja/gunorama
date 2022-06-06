package rs.sbnz.gunorama.service;


import lombok.extern.slf4j.Slf4j;
import org.drools.template.ObjectDataCompiler;
import org.springframework.stereotype.Service;
import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;
import rs.sbnz.gunorama.model.templates.OdredjivanjeDozvoljenihKalibaraTemplateModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RuleService {


    public List<String> readAllRules() {
        List<String> rules = new ArrayList<>();
        File ruleDirectory = new File("../gunorama-rules/src/main/resources/rs/sbnz/rules");
        for (File file : ruleDirectory.listFiles()) {
            if (file.getName().equals("OdredjivanjeDozvoljenihZeljenihKalibara.drl"))
                continue;
            rules.add(this.readRule(file.getName().replace(".drl", "")));
        }
        return rules;
    }

    public List<String> readRules(String... ruleFileNames) {
        List<String> rules = new ArrayList<>();
        for (String ruleFileName : ruleFileNames)
            rules.add(this.readRule(ruleFileName));
        return rules;
    }


    public String readRule(String ruleFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(String.format("../gunorama-rules/src/main/resources/rs/sbnz/rules/%s.drl", ruleFileName)))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
            log.info("Procitao pravilo: " + ruleFileName);
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String compileOdredjivanjeKalibaraTemplate(Double min, Double max) {
        List<OdredjivanjeDozvoljenihKalibaraTemplateModel> templateModels = new ArrayList<>();
        for (KonkretnaNamjena konkretnaNamjena : KonkretnaNamjena.values()) {
            templateModels.add(new OdredjivanjeDozvoljenihKalibaraTemplateModel(konkretnaNamjena, min, max));
        }
        log.info("== TEMPLATE ==");
        try (InputStream inputStream = new FileInputStream("../gunorama-rules/src/main/resources/rs/sbnz/templates/OdredjivanjeDozvoljenihZeljenihKalibara.drt")) {
            ObjectDataCompiler compiler = new ObjectDataCompiler();
            String drl = compiler.compile(templateModels, inputStream);
            log.info("Compilation successful.");
            log.info(drl);
            return drl;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
