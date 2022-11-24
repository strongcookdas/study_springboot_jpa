package com.jpa.study_springboot_jpa.hospital.repository;

import com.jpa.study_springboot_jpa.hospital.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
}
