package com.cgi.odsc.nhs.patient.controller;

import com.cgi.odsc.nhs.patient.domain.Patient;
import com.cgi.odsc.nhs.exception.NHSException;
import com.cgi.odsc.nhs.patient.service.IPatientService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


    @RequestMapping(value = "/patientList/update",method = RequestMethod.POST)
    public ModelAndView updatePatient(@RequestParam("id") int id
                                      )throws NHSException
    {
        //Patient patient= patientService.getPatientById(id);

        ModelAndView model = new ModelAndView();
        model.addObject("patient",patientService.getPatientById(id));
        model.addObject("view","/patientForm" );

        return model;
    }

    @RequestMapping(value = "patientList/delete",method = RequestMethod.POST)
    public String delete(@RequestParam("patient") int id){
       try {
           Patient patient = patientService.getPatientById(id);
           patientService.deletePatient(patient);
           return "redirect:/patientList";
       }
       catch (NHSException nhsException){
           log.info(nhsException.getMessage());
           return "/error";
       }
    }


    @RequestMapping(value = "/error")
    public String error(){
        return "access-denied";
    }

}
