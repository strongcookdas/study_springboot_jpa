package com.jpa.study_springboot_jpa.service.hospital;

import com.jpa.study_springboot_jpa.domain.dto.hospital.HospitalResponse;
import com.jpa.study_springboot_jpa.domain.entity.hospital.Hospital;
import com.jpa.study_springboot_jpa.repository.hospital.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public Page<Hospital> getHospitalList(Pageable pageable){
        Page<Hospital> lists = hospitalRepository.findAll(pageable);
        return lists;
    }

    public HospitalResponse getHospitalPage(Integer id){
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
        HospitalResponse hospitalResponse = null;
        if(!optionalHospital.isEmpty()){
            Hospital hospital = optionalHospital.get();
            hospitalResponse = HospitalResponse.of(hospital.getId(),hospital.getHospitalName(),hospital.getRoadNameAddress(),hospital.getReviews());
        }
        return hospitalResponse;
    }
}
