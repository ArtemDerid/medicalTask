package com.example.demo.controller;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.Patient;
import com.example.demo.domain.Sex;
import com.example.demo.service.PatientService;

@Controller
@CrossOrigin
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@RequestMapping(value ="/home", method = RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("patients", patientService.getAllPatients());

		return mav;
	}
	
	@RequestMapping(value = "/addPatient", method = RequestMethod.POST)
	public ModelAndView createPatient( 
			@RequestParam String firstName, 
			@RequestParam String lastName,
			@RequestParam Date dateOfBirth, 
			@RequestParam Integer age, 
			@RequestParam String sex,
			@RequestParam String country,
			@RequestParam String state,
			@RequestParam String address) throws IOException {	
		
		Sex sexOfPatient = null;
		if(sex.equalsIgnoreCase("male")) {
			sexOfPatient = Sex.Male;
		}
		if(sex.equalsIgnoreCase("female")) {
			sexOfPatient = Sex.Female;
		}
		Patient patient = new Patient(firstName, lastName, dateOfBirth, age, sexOfPatient, country, state, address);
		patientService.create(patient);
		return new ModelAndView("redirect:/home");
	}
	
	@RequestMapping(value = "/updatePatient", method = RequestMethod.GET)
	public ModelAndView updatePatient(@RequestParam("patientId") Integer patientId) {
		Patient patient = patientService.findPatient(patientId);
		ModelAndView mav = new ModelAndView("updatePatient");
		mav.addObject("patient", patient);
		return mav;
	}
	
	@RequestMapping(value = "/updatePatient", method = RequestMethod.POST)
	public ModelAndView updatePatient(Patient patient) throws IOException {	
		patientService.update(patient.getId());
		return new ModelAndView("redirect:/home");
	}
}
