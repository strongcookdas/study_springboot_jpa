package com.jpa.study_springboot_jpa.hospital.domain.dto;

import com.jpa.study_springboot_jpa.hospital.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class HospitalResponse {
    private Integer id;
    private String hospitalName;
    private String roadNameAddress;
    private List<ReviewResponse> reviews;
    public static HospitalResponse of(Integer id, String hospitalName, String roadNameAddress, List<ReviewResponse> reviews){
        return new HospitalResponse(id,hospitalName,roadNameAddress,reviews);
    }

}
