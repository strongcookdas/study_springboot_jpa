package com.jpa.study_springboot_jpa.domain.dto.hospital;

import com.jpa.study_springboot_jpa.domain.entity.hospital.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class HospitalResponse {
    private Integer id;
    private String hospitalName;
    private String roadNameAddress;
    private List<Review> reviews;
    public static HospitalResponse of(Integer id, String hospitalName, String roadNameAddress, List<Review> reviews){
        return new HospitalResponse(id,hospitalName,roadNameAddress,reviews);
    }

}
