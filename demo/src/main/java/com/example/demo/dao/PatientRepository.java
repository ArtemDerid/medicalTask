package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{

}
