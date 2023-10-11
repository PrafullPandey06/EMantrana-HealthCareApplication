package com.example.emantrana.repository;

import com.example.emantrana.models.Doctor;
import com.example.emantrana.models.Doctor_Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface Patient_DoctorPatientRepo extends JpaRepository<Doctor_Patient, Long> {
    List<Doctor_Patient> findByDoctor(Doctor doctor);
    Doctor_Patient findByDayAndPreseciptionDate(String day, Date preseciptionDate);

    @Query(value = "Select * from doctor_patient where preseciption_date=?1 and doctor_id=?2", nativeQuery = true)
    public Doctor_Patient findBYPreseciptionDate(Date d,Long id);


}
// // Patient : Doctor_Patient = 1 : N
