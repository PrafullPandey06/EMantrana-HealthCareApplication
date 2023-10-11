package com.example.emantrana.Admin.service;

import com.example.emantrana.Admin.dto.*;
import com.example.emantrana.jwt.JwtService;
import com.example.emantrana.models.*;
import com.example.emantrana.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DoctorRepo doctorRepository;
    @Autowired
    private DoctorTimeTableRepo doctimeRepo;
    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private Patient_DoctorPatientRepo patient_doctorPatientRepo;
    @Autowired
    private JwtService jwtService;

    public LoginResponseDTO verifyAdmin(LoginAdminDTO request) {
        Admin admin = adminRepo.findByEmail(request.getEmail());
        if (admin == null) {
            throw new RuntimeException("Admin not found");
        }
        String token = jwtService.createAdminJwt(admin.getEmail(), admin.getRoles());
        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setAdminId(admin.getId());
        return response;
    }

    public void addDoctor(DoctorRegistrationRequestDTO doctor) {
        String password = "abcd11$";
        Doctor doc = new Doctor(doctor.getFname(), doctor.getLname(), doctor.getEmail(), doctor.getType(), "abcd11$",
                doctor.getPh_number(), 0, "ROLE_DOCTOR");
        doc.setPassword(passwordEncoder.encode(doc.getPassword()));
        doc.setEmail(passwordEncoder.encode((doc.getEmail())));
        doc = doctorRepository.save(doc);
        Long id = doc.getId();
        List<TimeTableDTO> t = doctor.getTimeTable();
        for (Object o : t) {
            TimeTableDTO timeTableDTO = (TimeTableDTO) o;
            java.sql.Time tin = new java.sql.Time(timeTableDTO.getTime_in() * 1000 - (5 * 3600 + 30 * 60) * 1000);
            java.sql.Time tout = new java.sql.Time(timeTableDTO.getTime_out() * 1000 - (5 * 3600 + 30 * 60) * 1000);
            DoctorTimeTable timeTable = new DoctorTimeTable(timeTableDTO.getDay(),
                    tin,
                    tout,
                    doc);
            doctimeRepo.save(timeTable);
        }
    }

    public GetDoctorResponseDTO getDoctor(GetDoctorRequestDTO email) {
        Doctor doc = doctorRepository.findByEmail(email.getEmail());
        List<DoctorTimeTable> t = doc.getDoctorTimeTable();
        System.out.println(t);
        for (DoctorTimeTable i : t) {
            i.setDoctor(null);
        }
        System.out.println(t);
        GetDoctorResponseDTO response = new GetDoctorResponseDTO(doc.getId(), doc.getFname(), doc.getLname(),
                doc.getType(), doc.getEmail(), doc.getPhone(), doc.getPatientCount(), t);

        return response;
    }


    public void updateDoctor(DoctorUpdateDTO doctor) {
        doctorRepository.updateDoctor(doctor.getFname(), doctor.getLname(), doctor.getEmail(), doctor.getPh_number(),
                doctor.getType(), doctor.getId());
        doctimeRepo.deleteByDoctorId(doctor.getId());

        List<TimeTableDTO> t = doctor.getTimeTable();
        Doctor doc = new Doctor(doctor.getId());
        for (Object o : t) {
            TimeTableDTO timeTableDTO = (TimeTableDTO) o;
            java.sql.Time tin = new java.sql.Time(timeTableDTO.getTime_in() * 1000 - (5 * 3600 + 30 * 60) * 1000);
            java.sql.Time tout = new java.sql.Time(timeTableDTO.getTime_out() * 1000 - (5 * 3600 + 30 * 60) * 1000);
            DoctorTimeTable timeTable = new DoctorTimeTable(timeTableDTO.getDay(),
                    tin,
                    tout,
                    doc);
            doctimeRepo.save(timeTable);
        }
    }

    public void deleteDoctor(GetDoctorRequestDTO email) {
        doctorRepository.deleteByEmail(email.getEmail());
    }

    public Admin findAdminByemail(String email) {
        return adminRepo.findByEmail(email);
    }


    public followupdto getPatient(ViewPatientRequestDTO getPatientRequestDTO) throws ParseException {
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d=formatter.parse(getPatientRequestDTO.getPreseciptionDate());
        System.out.println(d);
        System.out.println(new Date());
        Doctor doctor= doctorRepository.findByEmail(getPatientRequestDTO.getEmail());
        Long id=doctor.getId();
        Doctor_Patient doctor_patient = patient_doctorPatientRepo.findBYPreseciptionDate(
                new Timestamp(d.getTime()),id);
        Long l = 1L;
        Doctor_Patient dp = patient_doctorPatientRepo.findById(l).get();
        System.out.println(doctor_patient.getId());
        Patient patient = doctor_patient.getPatient();
        System.out.println(patient.getPhoneNumber());
        followupdto follow = new followupdto();
        follow.setFname(patient.getFname());
        follow.setLname(patient.getLname());
        follow.setPhoneNumber(patient.getPhoneNumber());
        return follow;
/*     //   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
            Date date = formatter.parse(dateString);
            System.out.println(date); // prints something like "Wed Apr 20 00:17:40 PDT 2023"
//            Doctor_Patient doctor_patient = patient_doctorPatientRepo.
//                    findByDayAndPreseciptionDate(getPatientRequestDTO.getDay(),
//                            date);
//            Patient patient = patientRepo.findById(doctor_patient.getPatient().getId()).get();
//            System.out.println(patient);*/
    }

}

