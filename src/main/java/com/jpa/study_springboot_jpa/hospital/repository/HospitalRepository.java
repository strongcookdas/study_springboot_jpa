package com.jpa.study_springboot_jpa.hospital.repository;

import com.jpa.study_springboot_jpa.hospital.domain.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital,Integer> {
}
