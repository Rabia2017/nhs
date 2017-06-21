package com.cgi.odsc.nhs.patient.dao;

import com.cgi.odsc.nhs.patient.domain.Patient;
import com.cgi.odsc.nhs.patient.dao.IPatientDao;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import static org.mockito.Mockito.verify;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by rabia on 18/06/17.
 */
//@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class PatientDaoTest {

    @Mock
    public HibernateTemplate hibernateTemplate;
    @Mock
    public SessionFactory sessionFactory;
    @Mock
    public DataSource dataSource;

    @Mock
    public HibernateTransactionManager hibTransMan;

    @Before
    public void setUp(){

    }
    @Mock
    IPatientDao dao;

    @Test
    public void savePatient(){

        Patient patient = new Patient(1,"Alpha");

        doNothing().when(hibernateTemplate).saveOrUpdate(patient);
        hibernateTemplate.saveOrUpdate(patient);

        when(hibernateTemplate.get(Patient.class,patient.getId())).thenReturn(patient);
        Patient actual = hibernateTemplate.get(Patient.class,patient.getId());

        verify(hibernateTemplate).saveOrUpdate(patient);
        assertSame(patient, actual);
    }

    @Test
    public void deletePatient(){
        Patient patient = new Patient(1,"Alpha");

        doNothing().when(hibernateTemplate).delete(patient);
        hibernateTemplate.delete(patient);

        when(hibernateTemplate.get(Patient.class,patient.getId())).thenReturn(null);
        Patient actual = hibernateTemplate.get(Patient.class,patient.getId());

        assertSame(null, actual);

    }
    List<Patient> patients;


    @Test
    public void listAllPatientsTest(){
        patients = new ArrayList<Patient>();

        patients = hibernateTemplate.loadAll(Patient.class);
        assertEquals(0,patients.size());
        patients.add(Patient.builder().id(1).name("alpha").build());
        patients.add(Patient.builder().id(2).name("beta").build());
        patients.add(Patient.builder().id(3).name("theta").build());

        Mockito.when(hibernateTemplate.loadAll(Patient.class)).thenReturn(patients);
        patients = hibernateTemplate.loadAll(Patient.class);
        assertNotNull(patients);
        assertEquals(3,patients.size());
    }

}
