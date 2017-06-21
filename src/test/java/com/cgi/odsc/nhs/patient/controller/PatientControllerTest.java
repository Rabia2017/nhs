package com.cgi.odsc.nhs.patient.controller;

import com.cgi.odsc.nhs.patient.service.PatientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by rabia on 18/06/17.
*/
@RunWith(MockitoJUnitRunner.class)
public class PatientControllerTest {
    @Mock
    PatientService patientService;

    @Before
    public void SetUp(){}

    @Test
    public void getPatientsTest(){

    }

}
