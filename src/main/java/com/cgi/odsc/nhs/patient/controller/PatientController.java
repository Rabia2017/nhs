package com.cgi.odsc.nhs.patient.controller;

import com.cgi.odsc.nhs.patient.domain.Patient;
import com.cgi.odsc.nhs.exception.NHSException;
import com.cgi.odsc.nhs.patient.service.IPatientService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rabia on 17/06/17.
 */
@Controller
@NoArgsConstructor
@Slf4j
public class PatientController {
    @Autowired
    private IPatientService patientService;

    void PatientController(IPatientService patientService){
        this.patientService =patientService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView welcome() {
        return new ModelAndView("index");
    }


    @RequestMapping(value = "/patientList", method= RequestMethod.GET)
    public ModelAndView getlists( ){

        ModelAndView model = new ModelAndView();
        model.addObject("patients",patientService.getPatients());
        model.addObject("view","/patientList" );
        return model;
    }

    @RequestMapping(value = "/patientForm", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("patientForm", "patient", new Patient());
    }


    @RequestMapping(value = "/submit",params = "submit", method= RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute("patient") Patient patient){
        patientService.savePatient(patient);
        return new ModelAndView("redirect:/patientList");
    }

    @RequestMapping(value = "/submit", params = "cancel", method = RequestMethod.POST)
    public ModelAndView cancel(HttpServletRequest request) {
        return new ModelAndView("redirect:/patientList");
    }


    @RequestMapping(value = "/patientForm/{id}",method = RequestMethod.POST)
    public ModelAndView updatePatient(@PathVariable int id,
                                      HttpServletRequest request, @ModelAttribute("patient") Patient patient)throws NHSException
    {
        //Patient patient= patientService.getPatientById(id);

        ModelAndView model = new ModelAndView();
        model.addObject("patient",patientService.getPatientById(id));
        model.addObject("view","/patientForm" );

        return model;
    }

    @RequestMapping(value = "/patientForm/{patient}",method = RequestMethod.DELETE)
    public ModelAndView deletePatient(@ModelAttribute("patient") Patient patient, HttpServletRequest request)throws NHSException
    {
        patientService.deletePatient(patient);
        return new ModelAndView("redirect:/patientList");
    }


    @RequestMapping(value = "/error")
    public String error(){
        return "access-denied";
    }

}
