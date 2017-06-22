package com.cgi.odsc.nhs.patient.controller;

import com.cgi.odsc.nhs.config.AppConfig;
import com.cgi.odsc.nhs.config.DBConfig;
import com.cgi.odsc.nhs.exception.NHSException;
import com.cgi.odsc.nhs.patient.dao.IPatientDao;
import com.cgi.odsc.nhs.patient.dao.PatientDao;
import com.cgi.odsc.nhs.patient.domain.Patient;
import com.cgi.odsc.nhs.patient.service.IPatientService;
import com.cgi.odsc.nhs.patient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.stubbing.answers.CallsRealMethods;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by rabia on 18/06/17.
*/
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class,DBConfig.class})
@WebAppConfiguration
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class PatientControllerTest {
    @Mock
    IPatientService mockPatientService;

    @Mock
    IPatientDao patientDao;

    @InjectMocks
    @Resource
    PatientController patientController;


    MockMvc mockMvc;

    @Mock
    View mockView;

    @Mock
    Patient patient;


    @Before
    public void setUp() throws NHSException{
        patientDao = new PatientDao();
        mockPatientService =new PatientService(patientDao);

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(patientController)
                .setSingleView(mockView)
                .build();
    }

    @Test
    public void getPatientsTest() throws Exception{
        List<Patient> expectedPatients  =   asList(new Patient(1,"abc"));
        when(mockPatientService.getPatients()).thenReturn(expectedPatients);

        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("patients",expectedPatients))
                .andExpect(view().name("patients"));
        Mockito.verify(mockPatientService,atMost(1));
    }


    @Test
    public void deleteTestHappyPath() throws Exception{
        patient  =   new Patient(1,"abc");

        /* use this logic in saveOrUpdate method

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                return null;
            }
        }).when(mockPatientService).deletePatient(patient);
*/
         mockMvc.perform(post("/patients/delete").param("patient","1"))
                .andDo(print())
                .andExpect(view().name("redirect:/patients"))
                .andReturn();

         verify(mockPatientService,atMost(1));

    }

}
