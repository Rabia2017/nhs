package com.cgi.odsc.nhs.patient.dao;

import com.cgi.odsc.nhs.patient.domain.Patient;
import com.cgi.odsc.nhs.patient.dao.IPatientDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by rabia on 18/06/17.
 */
//@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class PatientDaoTest {

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Mock
    IPatientDao patientDao;


    @Before
    public void setUp(){
        //patientDao= new PatientDao();
        hibernateTemplate = new HibernateTemplate();
    }

    @Test
    public void savePatient(){
        Patient patient = new Patient(1,"Alpha");
        when(patientDao.saveOrUpdatePatient(patient)).thenReturn(patient);
        patient = patientDao.saveOrUpdatePatient(patient);
        assertEquals(new Long(1),new Long(patient.getId()));
    }

    @Test
    public void deletePatient(){
        Patient patient = new Patient(1,"Alpha");
        //patient =patientDao.deletePatient(patient);
        //assertEquals(null,patient);
        when(patientDao.deletePatient(patient)).thenReturn(null);
        patient = patientDao.deletePatient(patient);
        assertEquals(null,patient);

    }
    List<Patient> patients;


    @Test
    public void listAllPatientsTest(){
        patients = new ArrayList<Patient>();

        patients = patientDao.listAllPatients();
        assertEquals(0,patients.size());
        patients.add(Patient.builder().id(1).name("alpha").build());
        patients.add(Patient.builder().id(2).name("beta").build());
        patients.add(Patient.builder().id(3).name("theta").build());

        Mockito.when(patientDao.listAllPatients()).thenReturn(patients);
        patients = patientDao.listAllPatients();
        assertNotNull(patients);
        assertEquals(3,patients.size());
    }

}
