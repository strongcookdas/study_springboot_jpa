package com.jpa.study_springboot_jpa.domain.dto.hospital;

import com.jpa.study_springboot_jpa.domain.entity.hospital.Hospital;
import com.jpa.study_springboot_jpa.domain.entity.hospital.Review;
import lombok.Getter;

@Getter
public class ReviewRequest {
    private String title;
    private String content;
    private String name;

    public Review toEntity(Hospital hospital){
        return new Review(this.title,this.content,this.name,hospital);
    }
}
