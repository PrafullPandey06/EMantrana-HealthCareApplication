package com.example.emantrana.Admin.controller;

import com.example.emantrana.Admin.dto.*;
import com.example.emantrana.Admin.service.AdminService;
import com.example.emantrana.models.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestMapping("/admin")
@RestController
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> verifyPatient(
            @RequestBody LoginAdminDTO request)
    {
        LoginResponseDTO verifiedAdmin = adminService.verifyAdmin(request);
        return ResponseEntity.ok().body(verifiedAdmin);
    }
    @PostMapping(value = "/add_doctor")
    public void addDoctor(@RequestBody DoctorRegistrationRequestDTO doctor){
        adminService.addDoctor(doctor);
    }
    @GetMapping(value = "/get_doctor/{email}")
    public GetDoctorResponseDTO getDoctor(@PathVariable("email") String email){
        GetDoctorRequestDTO getDoctorRequestDTO=new GetDoctorRequestDTO(email);
        GetDoctorResponseDTO getDoctorResponseDTO=adminService.getDoctor(getDoctorRequestDTO);
        return getDoctorResponseDTO;
    }

    @PutMapping (value = "/update_doctor")
    public void updateDoctor(@RequestBody DoctorUpdateDTO doctor){
        adminService.updateDoctor(doctor);
    }

    @DeleteMapping  (value = "/delete_doctor/{email}")
    public void deleteDoctor(@PathVariable("email") String email){
        GetDoctorRequestDTO getDoctorRequestDTO=new GetDoctorRequestDTO(email);
        adminService.deleteDoctor(getDoctorRequestDTO);
    }

    @GetMapping(value = "/getPatient")
    public followupdto getPatient(@RequestBody ViewPatientRequestDTO getPatientRequestDTO) throws ParseException {
      //  Patie'nt patient =
//        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
//        Date d=formatter.parse("2023-05-03 01:06:01.507000");
//        Date d1=formatter.parse(getPatientRequestDTO.getPreseciptionDate());
//        System.out.println(d);
//        System.out.println(d1);
//        System.out.println(d1.compareTo(d));
      return adminService.getPatient(getPatientRequestDTO);
    }
}
