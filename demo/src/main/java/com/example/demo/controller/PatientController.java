package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Patient;
import com.example.demo.service.CommentService;
import com.example.demo.service.PatientService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@Autowired
	private CommentService commentService;

//	@RequestMapping(value = "/patients", method = RequestMethod.GET)
//	public ModelAndView welcome() {
//		ModelAndView mav = new ModelAndView("home");
//		mav.addObject("patients", patientService.getAllPatients());
//
//		return mav;
//	}
	
	@PostMapping("/addComment")
	private Comment createComment(@RequestBody Comment comment, @RequestParam Integer patientId) {
		Patient patientFromDB = patientService.findPatient(patientId);
		comment.setPatient(patientFromDB);
		return commentService.create(comment);
	}
	
	@GetMapping("/patients")
	public List<Patient> showAllPatients(){
		return patientService.getAllPatients();
	}
	
	@GetMapping("/user-details")
	public List<Comment> showComments(@RequestBody Patient patient){
		return commentService.findCommentsOfPatient(patient);
	}

//	@RequestMapping(value = "/addPatient", method = RequestMethod.POST)
//	public ModelAndView createPatient(@RequestParam String firstName, @RequestParam String lastName,
//			@RequestParam Date dateOfBirth, @RequestParam Integer age, @RequestParam String sex,
//			@RequestParam String country, @RequestParam String state, @RequestParam String address) throws IOException {
//
//		Sex sexOfPatient = null;
//		if (sex.equalsIgnoreCase("male")) {
//			sexOfPatient = Sex.Male;
//		}
//		if (sex.equalsIgnoreCase("female")) {
//			sexOfPatient = Sex.Female;
//		}
//		Patient patient = new Patient(firstName, lastName, dateOfBirth, age, sexOfPatient, country, state, address);
//		patientService.create(patient);
//		return new ModelAndView("redirect:/home");
//	}
	
	@PostMapping("/patients")
	public Patient createPatient(@RequestBody Patient patient) {
		return patientService.create(patient);
	}
	
	@PostMapping("/update")
	public Patient updatePatient(@RequestBody Patient patient) {
		return patientService.update(patient.getId());
	}

//	@RequestMapping(value = "/updatePatient", method = RequestMethod.GET)
//	public ModelAndView updatePatient(@RequestParam("patientId") Integer patientId) {
//		Patient patient = patientService.findPatient(patientId);
//		ModelAndView mav = new ModelAndView("updatePatient");
//		mav.addObject("patient", patient);
//		return mav;
//	}
//
//	@RequestMapping(value = "/updatePatient", method = RequestMethod.POST)
//	public ModelAndView updatePatient(Patient patient) throws IOException {
//		patientService.update(patient.getId());
//		return new ModelAndView("redirect:/home");
//	}
}
