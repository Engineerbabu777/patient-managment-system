package com.pm.patientservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pm.patientservice.dto.PatientRequestDto;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pm.patientservice.dto.PatientUpdateRequestDTO;



@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        return ResponseEntity.ok().body(patientService.getAllPatients());
    }

    @PostMapping("")
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDto patientRequestDto) {
        return ResponseEntity.ok().body(patientService.createPatient(patientRequestDto));
    }

    @PutMapping("/{id}") 
    public ResponseEntity<PatientResponseDTO> updatePatient(@Valid @RequestBody PatientUpdateRequestDTO patientRequestDTO, @PathVariable String id){
        return ResponseEntity.ok().body(patientService.updatePatient(id, patientRequestDTO));
    }
    

}
