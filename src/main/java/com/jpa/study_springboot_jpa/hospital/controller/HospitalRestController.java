package com.jpa.study_springboot_jpa.hospital.controller;

import com.jpa.study_springboot_jpa.hospital.domain.dto.HospitalResponse;
import com.jpa.study_springboot_jpa.hospital.domain.dto.ReviewRequest;
import com.jpa.study_springboot_jpa.hospital.domain.dto.ReviewResponse;
import com.jpa.study_springboot_jpa.hospital.domain.entity.Hospital;
import com.jpa.study_springboot_jpa.hospital.service.HospitalService;
import com.jpa.study_springboot_jpa.hospital.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hospital")
@RequiredArgsConstructor
public class HospitalRestController {

    private final HospitalService hospitalService;
    private final ReviewService reviewService;

    @GetMapping("")
    public ResponseEntity<Page<Hospital>> hospitalListPage(Pageable pageable){
        return ResponseEntity.ok().body(hospitalService.getHospitalList(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> hospitalPage(@PathVariable Integer id){
        return ResponseEntity.ok().body(hospitalService.getHospitalPage(id));
    }

    @GetMapping("/{id}/review")
    public ResponseEntity<List<ReviewResponse>> hospitalReviewsPage(@PathVariable Integer id){
        return ResponseEntity.ok().body(reviewService.getReviewsPage(id));
    }

    @PostMapping("/{id}/review")
    public ResponseEntity<ReviewResponse> reviewPage(@PathVariable Integer id, @RequestBody ReviewRequest reviewRequest){
        return ResponseEntity.ok().body(reviewService.saveReview(id,reviewRequest));
    }
}
