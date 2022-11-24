package com.jpa.study_springboot_jpa.hospital.domain.dto;

import com.jpa.study_springboot_jpa.hospital.domain.entity.Hospital;
import com.jpa.study_springboot_jpa.hospital.domain.entity.Review;
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
