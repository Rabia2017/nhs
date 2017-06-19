package com.cgi.odsc.nhs.patient.service;

import com.cgi.odsc.nhs.patient.domain.Patient;
import com.cgi.odsc.nhs.exception.NHSException;

import java.util.List;

/**
 * Created by rabia on 17/06/17.
 */
public interface IPatientService {

    List<Patient> getPatients();
    Patient savePatient(Patient patient);
    Patient deletePatient(Patient patient);
    public Patient getPatientById(Integer id) throws NHSException;
    public void updatePatient(Patient patient);
}