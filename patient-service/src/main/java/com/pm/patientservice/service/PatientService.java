package com.pm.patientservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pm.patientservice.dto.PatientRequestDto;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.PatientUpdateRequestDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.InavlidUUIDException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDto patientRequest) {
        if (patientRepository.existsByEmail(patientRequest.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "A patient of this email already exists ===> " + patientRequest.getEmail());
        }
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequest));

        return PatientMapper.toDTO(newPatient);
    }

    public PatientResponseDTO updatePatient(String id, PatientUpdateRequestDTO patientRequestDTO) {

        UUID patientID;
        try {
            patientID = UUID.fromString(id);
            
        } catch (Exception e) {
            throw new InavlidUUIDException("Invlaid UUID format ====>>"+id);
        }

        Patient patient = patientRepository.findById(patientID)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID ===> " + id));

        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "A patient of this email already exists ===> " + patientRequestDTO.getEmail());
        }

        if (patientRequestDTO.getName() != null) {
            patient.setName(patientRequestDTO.getName());
        }

        if (patientRequestDTO.getAddress() != null) {
            patient.setAddress(patientRequestDTO.getAddress());
        }

        if (patientRequestDTO.getEmail() != null) {
            patient.setEmail(patientRequestDTO.getEmail());
        }

        // patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);

        return PatientMapper.toDTO(updatedPatient);

    }

}
