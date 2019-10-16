package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PatientRepository;
import com.example.demo.domain.Patient;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	public Patient create(Patient patient) {
		return patientRepository.save(patient);
	}
	
	public Patient update(Integer id) {
		Patient patient = new Patient();
		Patient patientFromDB = patientRepository.getOne(id);
		patient.setFirstName(patientFromDB.getFirstName());
		patient.setLastName(patientFromDB.getLastName());
		patient.setAge(patientFromDB.getAge());
		patient.setSex(patientFromDB.getSex());
		patient.setCountry(patientFromDB.getCountry());
		patient.setState(patientFromDB.getState());
		patient.setAddress(patientFromDB.getAddress());
		patient.setId(patientFromDB.getId());
		return patientRepository.save(patient);
	}
	
	public List<Patient> getAllPatients(){
		return patientRepository.findAll();
	}
	
	public Patient findPatient(Integer id) {
		return patientRepository.getOne(id);
	}

}
