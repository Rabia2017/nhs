package com.cgi.odsc.nhs.patient.service;

import com.cgi.odsc.nhs.patient.dao.PatientDao;
import com.cgi.odsc.nhs.patient.domain.Patient;
import com.cgi.odsc.nhs.exception.NHSException;
import com.cgi.odsc.nhs.patient.service.IPatientService;
import com.cgi.odsc.nhs.patient.service.PatientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by rabia on 18/06/17.
 */
//@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

    List<Patient> patients;
    @Mock
    private PatientDao patientDao;
    private IPatientService patientService;

    @Before
    public void doSetUp() throws NHSException{
        patientService = new PatientService(patientDao);

        patients = new ArrayList<Patient>();
        patients.add(Patient.builder().id(1).name("alpha").build());
        patients.add(Patient.builder().id(2).name("beta").build());
        patients.add(Patient.builder().id(3).name("theta").build());

    }

    @Test
    public void testSavePatient() {

        Patient patient = new Patient();
        patient.setName("John");

        //Mockito.when(patientDao.savePatient(patient)).thenReturn(patient);

        // In code below patient first argument Id is initialized
        Mockito.when(patientService.saveOrUpdatePatient(patient))
                .thenAnswer(new Answer<Patient>() {
                    @Override
                    public Patient answer(InvocationOnMock invocation) {
                        Patient p1 = (Patient) invocation.getArguments()[0];
                        p1.setId(1);
                        return p1;
                    }
                });

        assertEquals(null,patient.getId());
        //log.info("before save ");
        patient = patientService.saveOrUpdatePatient(patient);
        assertNotEquals(new Long(0),new Long(patient.getId()));
        //log.info("after save " +patient.getId());
        assertTrue(patient.getId() > 0);
    }

    @Test
    public void deletePatient(){
        Patient patient = new Patient();
        patient.setId(1);
        patient.setName("John");

        assertEquals(new Long(1),new Long(patient.getId()));
        patient = patientService.deletePatient(patient);
        assertNotNull(patient);
    }




    @Test
    public void getPatientsTest(){
        int size = patientService.getPatients().size();
        org.junit.Assert.assertSame(0,size);

        Mockito.when(patientService.getPatients()).thenReturn(patients);
        patients=patientService.getPatients();

        org.junit.Assert.assertSame(3,patients.size());

    }





}
