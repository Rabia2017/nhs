package com.cgi.odsc.nhs.patient.domain;

import com.cgi.odsc.nhs.patient.domain.Patient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


/**
 * Created by rabia on 19/06/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class PatientTest {

    @Mock
    private Patient patient;

    @Before
    public void SetUp(){
        patient = new Patient(1,"alpha");
    }

    @Test
    public void PatientEntityTest() throws Exception{
        assertSame(1,patient.getId());
        assertEquals("alpha", patient.getName());
    }

}
