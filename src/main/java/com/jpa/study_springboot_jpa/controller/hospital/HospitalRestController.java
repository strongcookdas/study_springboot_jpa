package com.jpa.study_springboot_jpa.controller.hospital;

import com.jpa.study_springboot_jpa.domain.dto.hospital.HospitalResponse;
import com.jpa.study_springboot_jpa.domain.dto.hospital.ReviewRequest;
import com.jpa.study_springboot_jpa.domain.dto.hospital.ReviewResponse;
import com.jpa.study_springboot_jpa.domain.entity.hospital.Hospital;
import com.jpa.study_springboot_jpa.domain.entity.hospital.Review;
import com.jpa.study_springboot_jpa.service.hospital.HospitalService;
import com.jpa.study_springboot_jpa.service.hospital.ReviewService;
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

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<Review>> hospitalReviewsPage(@PathVariable Integer id){
        return ResponseEntity.ok().body(reviewService.getReviewsPage(id));
    }

    @PostMapping("/{id}/review")
    public ResponseEntity<ReviewResponse> reviewPage(@PathVariable Integer id, @RequestBody ReviewRequest reviewRequest){
        return ResponseEntity.ok().body(reviewService.saveReview(id,reviewRequest));
    }
}
