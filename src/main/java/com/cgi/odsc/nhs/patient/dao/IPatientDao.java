package com.cgi.odsc.nhs.patient.dao;

import com.cgi.odsc.nhs.patient.domain.Patient;

import java.util.List;

/**
 * Created by rabia on 17/06/17.
 */
public interface IPatientDao {
     Patient savePatient(Patient patient);
     Patient deletePatient(Patient patient);
     List<Patient> listAllPatients();
     public Patient getPatientById(Integer patientId);
     public void updatePatient(Patient patient);
}
