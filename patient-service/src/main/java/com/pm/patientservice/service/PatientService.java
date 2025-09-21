package com.pm.patientservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pm.patientservice.dto.PatientRequestDto;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;

@Service
public class PatientService {


    private PatientRepository patientRepository;



    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getAllPatients(){
        List<Patient> patients = patientRepository.findAll();

        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDto patientRequest){
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequest));

        return PatientMapper.toDTO(newPatient);
    }

}
