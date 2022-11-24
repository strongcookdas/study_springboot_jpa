package com.jpa.study_springboot_jpa.domain.dto.hospital;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HospitalResponse {
    private Integer id;
    private String hospitalName;
    private String roadNameAddress;

    public static HospitalResponse of(Integer id, String hospitalName, String roadNameAddress){
        return new HospitalResponse(id,hospitalName,roadNameAddress);
    }

}
