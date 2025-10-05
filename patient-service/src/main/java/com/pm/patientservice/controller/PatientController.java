package com.pm.patientservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pm.patientservice.dto.PatientRequestDto;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.PatientUpdateRequestDTO;
import com.pm.patientservice.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "API for managing patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("")
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        return ResponseEntity.ok().body(patientService.getAllPatients());
    }

    @PostMapping("")
    @Operation(summary = "Create a new Patients")
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDto patientRequestDto) {
        return ResponseEntity.ok().body(patientService.createPatient(patientRequestDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Patients")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @Valid @RequestBody PatientUpdateRequestDTO patientRequestDTO, @PathVariable String id) {
        return ResponseEntity.ok().body(patientService.updatePatient(id, patientRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Patients")
    public ResponseEntity<Map<String, String>> deletePatientController(@PathVariable String id) {
        return ResponseEntity.ok().body(patientService.deletePatient(id));
    }

}
