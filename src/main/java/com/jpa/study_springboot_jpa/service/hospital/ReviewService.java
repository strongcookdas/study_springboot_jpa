package com.jpa.study_springboot_jpa.service.hospital;

import com.jpa.study_springboot_jpa.domain.dto.hospital.ReviewRequest;
import com.jpa.study_springboot_jpa.domain.dto.hospital.ReviewResponse;
import com.jpa.study_springboot_jpa.domain.entity.hospital.Hospital;
import com.jpa.study_springboot_jpa.domain.entity.hospital.Review;
import com.jpa.study_springboot_jpa.repository.hospital.HospitalRepository;
import com.jpa.study_springboot_jpa.repository.hospital.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final HospitalRepository hospitalRepository;

    public ReviewResponse saveReview(Integer id, ReviewRequest reivewRequest){
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
        if(optionalHospital.isEmpty())
            return null;
        Review review = reivewRequest.toEntity(optionalHospital.get());
        review = reviewRepository.save(review);
        return ReviewResponse.of(review.getId(),review.getHospital().getId(),review.getTitle(),review.getContent(),review.getName());
    }

    public ReviewResponse getReviewPage(Integer id){
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if(optionalReview.isEmpty())
            return null;
        Review review = optionalReview.get();
        return ReviewResponse.of(review.getId(),review.getHospital().getId(),review.getTitle(),review.getContent(),review.getName());
    }

    public List<Review> getReviewsPage(Integer id){
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
        if(optionalHospital.isEmpty())
            return null;
        return optionalHospital.get().getReviews();
    }
}
