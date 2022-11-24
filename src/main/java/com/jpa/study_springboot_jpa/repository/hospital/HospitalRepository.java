package com.jpa.study_springboot_jpa.repository.hospital;

import com.jpa.study_springboot_jpa.domain.entity.hospital.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital,Integer> {
}
