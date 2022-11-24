package com.jpa.study_springboot_jpa.repository.hospital;

import com.jpa.study_springboot_jpa.domain.entity.hospital.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
}
