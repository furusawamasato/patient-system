package com.example.patient_system.repository;

import com.example.patient_system.mapper.PatientMapper;
import com.example.patient_system.PatientSystemApplication;
import com.example.patient_system.entity.Patient;
import org.springframework.stereotype.Repository;
import org.thymeleaf.expression.Lists;

import java.util.List;

@Repository
public class PatientRepository {

    private final PatientMapper patientsMapper;

    public PatientRepository(PatientMapper patientsMapper) {
        this.patientsMapper = patientsMapper;
    }

    public List<Patient> getAllPatients() {
        return patientsMapper.selectAllPatients();
    }

    public void insertPatients(Patient patients) {
        patientsMapper.insertPatients(patients);
    }

    public Patient getPatientsById(long id) {
        return patientsMapper.selectPatientsById(id);
    }

    public void deletePatients(long id) {
        patientsMapper.deletePatients(id);
    }

    public void updatePatient(Patient patient) {
        patientsMapper.updatePatient(patient);
    }
}
