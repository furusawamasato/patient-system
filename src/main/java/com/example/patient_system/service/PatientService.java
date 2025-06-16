package com.example.patient_system.service;

import com.example.patient_system.repository.PatientRepository;

import com.example.patient_system.entity.Patient;
import com.example.patient_system.form.PatientForm;

import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientsRepository;
    
    public PatientService(PatientRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    public List<Patient> getAllPatients() {
        return patientsRepository.getAllPatients();
    }

    public void createPatients(PatientForm patientForm) {
        Patient patients = new Patient();
        patients.setName(patientForm.getName());
        patients.setAge(patientForm.getAge());
        patients.setGender(patientForm.getGender());
        patients.setBloodType(patientForm.getBloodType());
        patients.setDiseaseName(patientForm.getDiseaseName());
        patients.setSymptoms(patientForm.getSymptoms());

        patientsRepository.insertPatients(patients);
    }

    public Patient getPatientsById(long id) {
        return patientsRepository.getPatientsById(id);
    }

    public void deletePatients(long id) {
        patientsRepository.deletePatients(id);
    }

    public void updatePatient(long id, Patient patient) {
        if (id != patient.getId()) {
            throw new IllegalArgumentException("ID does not match");
        }
        patientsRepository.updatePatient(patient);
    }
}
