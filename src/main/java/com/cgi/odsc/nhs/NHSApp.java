package com.cgi.odsc.nhs;

import com.cgi.odsc.nhs.config.DBConfig;
import com.cgi.odsc.nhs.patient.domain.Patient;
import com.cgi.odsc.nhs.patient.service.IPatientService;
import com.cgi.odsc.nhs.patient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by rabia on 16/06/17.
 */
@Slf4j
public class NHSApp {

    public static void main(String[] args) {

       // log.info("********** Inside main *************");

       /* AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        //ctx.scan("com.cgi.odsc.nhs");

        ctx.register(DBConfig.class);
        ctx.refresh();

        IPatientService patientService = ctx.getBean(PatientService.class);

        patientService.savePatient(Patient
                .builder()
                .name("sabita").build());
                */
  //      ctx.close();*/
       // log.info("Done");
    }
}
