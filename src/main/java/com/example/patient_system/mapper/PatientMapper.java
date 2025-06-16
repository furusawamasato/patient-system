package com.example.patient_system.mapper;

import com.example.patient_system.entity.Patient;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

import org.thymeleaf.expression.Lists;

@Mapper
public interface PatientMapper {
    @Select("SELECT * FROM patients")
    List<Patient> selectAllPatients();

    @Insert("INSERT INTO patients (name, age, gender, bloodType, diseaseName, symptoms) VALUES (#{name}, #{age}, #{gender}, #{bloodType}, #{diseaseName}, #{symptoms})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertPatients(Patient patients);

    @Select("SELECT * FROM patients WHERE id = #{id}")
    Patient selectPatientsById(long id);

    @Delete("DELETE FROM patients WHERE id = #{id}")
    void deletePatients(long id);

    @Update("UPDATE patients SET name = #{name}, age = #{age}, gender = #{gender}, bloodType = #{bloodType}, diseaseName = #{diseaseName}, symptoms = #{symptoms} WHERE id = #{id}")
    void updatePatient(Patient patient);
}
