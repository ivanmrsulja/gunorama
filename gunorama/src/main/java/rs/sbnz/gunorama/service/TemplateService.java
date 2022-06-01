package rs.sbnz.gunorama.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.maven.shared.invoker.*;
import org.drools.template.ObjectDataCompiler;
import org.springframework.stereotype.Service;
import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;
import rs.sbnz.gunorama.model.templates.OdredjivanjeDozvoljenihKalibaraTemplateModel;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class TemplateService {

    public String compileOdredjivanjeKalibaraTemplate(){
        List<OdredjivanjeDozvoljenihKalibaraTemplateModel> templateModels = new ArrayList<>();
        for (KonkretnaNamjena konkretnaNamjena : KonkretnaNamjena.values()) {
            templateModels.add(new OdredjivanjeDozvoljenihKalibaraTemplateModel(konkretnaNamjena));
        }
        try (InputStream inputStream = new FileInputStream("../gunorama-rules/src/main/resources/rs/sbnz/templates/OdredjivanjeDozvoljenihKalibara.drt")) {
            ObjectDataCompiler compiler = new ObjectDataCompiler();
            String drl = compiler.compile(templateModels, inputStream);
            log.info("Compilation successful.");
            return drl;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeCompiledOdredjivanjeKalibaraTemplate(String compiledRules) throws IOException {
        File file = new File("../gunorama-rules/src/main/resources/rs/sbnz/rules/OdredjivanjeDozvoljenihKalibara.drl");
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(compiledRules);
        }
    }


    public void doJob() throws IOException {
        String drl = this.compileOdredjivanjeKalibaraTemplate();
        this.writeCompiledOdredjivanjeKalibaraTemplate(drl);
    }

    public void refresh() throws MavenInvocationException {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File("../gunorama-rules/pom.xml"));
        request.setGoals(Arrays.asList("clean", "install"));

        Invoker invoker = new DefaultInvoker();

        invoker.execute(request);
    }





}
