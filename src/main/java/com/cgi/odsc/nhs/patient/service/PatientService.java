package com.cgi.odsc.nhs.patient.service;

import com.cgi.odsc.nhs.patient.dao.IPatientDao;
import com.cgi.odsc.nhs.patient.domain.Patient;
import com.cgi.odsc.nhs.exception.NHSException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * Created by rabia on 17/06/17.
 */
@Service
@NoArgsConstructor
public class PatientService implements IPatientService {

    @Autowired
    private IPatientDao patientDao;

    public PatientService(IPatientDao patientDao) throws NHSException{
        if(!ObjectUtils.isEmpty(patientDao))
            this.patientDao =  patientDao;
        else
            throw new NHSException("patientDao cannnot be null");
    }

    public List<Patient> getPatients()
    {

        return patientDao.listAllPatients();
    }

    @Override
    public Patient savePatient(Patient patient) {
        if(!ObjectUtils.isEmpty(patient))
            patientDao.savePatient(patient);
        return patient;
    }

    @Override
    public Patient deletePatient(Patient patient) {
        if(!ObjectUtils.isEmpty(patient))
            patientDao.deletePatient(patient);
        return null;
    }

    public Patient getPatientById(Integer id) throws NHSException{
        if(ObjectUtils.isEmpty(id))
            new NHSException("Patient with {} does not exist"+id);
        return patientDao.getPatientById(id);
    }
    public void updatePatient(Patient patient){
        patientDao.updatePatient(patient);
    }
}