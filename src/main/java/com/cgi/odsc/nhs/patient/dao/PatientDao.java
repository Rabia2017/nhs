package com.cgi.odsc.nhs.patient.dao;

import com.cgi.odsc.nhs.patient.domain.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by rabia on 17/06/17.
 */

public class PatientDao implements IPatientDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional
    public Patient saveOrUpdatePatient(Patient patient) {
            hibernateTemplate.saveOrUpdate(patient);
            return patient;
    }

    @Override
    @Transactional
    public Patient deletePatient(Patient patient) {
           hibernateTemplate.delete(patient);
            return patient;
    }

    @Override
    @Transactional
    public List<Patient> listAllPatients() {
       List<Patient> patients=  hibernateTemplate.loadAll(Patient.class);
        return patients;
    }

    public Patient getPatientById(Integer patientId){
        return hibernateTemplate.get(Patient.class,patientId);
    }

    public void updatePatient(Patient patient){
        hibernateTemplate.update(patient);
    }

}
