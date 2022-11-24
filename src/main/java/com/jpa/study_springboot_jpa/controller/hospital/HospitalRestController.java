package com.jpa.study_springboot_jpa.controller.hospital;

import com.jpa.study_springboot_jpa.domain.dto.hospital.HospitalResponse;
import com.jpa.study_springboot_jpa.domain.entity.hospital.Hospital;
import com.jpa.study_springboot_jpa.service.hospital.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hospital")
@RequiredArgsConstructor
public class HospitalRestController {

    private final HospitalService hospitalService;

    @GetMapping("")
    public ResponseEntity<Page<Hospital>> hospitalListPage(Pageable pageable){
        return ResponseEntity.ok().body(hospitalService.getHospitalList(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> hospitalPage(@PathVariable Integer id){
        return ResponseEntity.ok().body(hospitalService.getHospitalPage(id));
    }
}
