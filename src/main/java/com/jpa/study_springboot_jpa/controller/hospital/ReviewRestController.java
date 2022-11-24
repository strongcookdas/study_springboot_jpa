package com.jpa.study_springboot_jpa.controller.hospital;

import com.jpa.study_springboot_jpa.domain.dto.hospital.ReviewResponse;
import com.jpa.study_springboot_jpa.service.hospital.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewRestController {

    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> reviewPage(@PathVariable Integer id){
        return ResponseEntity.ok().body(reviewService.getReviewPage(id));
    }
}
