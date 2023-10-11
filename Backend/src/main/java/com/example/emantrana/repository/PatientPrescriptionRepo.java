package com.example.emantrana.repository;

import com.example.emantrana.models.Doctor;
import com.example.emantrana.models.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientPrescriptionRepo extends JpaRepository<Prescription, Long> {
    Prescription findByPatientId(Long patientId);

    List<Prescription> findByDoctor(Doctor doctor);
}
// Patient : Prescription = 1 : N
