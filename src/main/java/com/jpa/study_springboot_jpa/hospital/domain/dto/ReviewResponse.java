package com.jpa.study_springboot_jpa.hospital.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReviewResponse {
    private Integer id;
    private Integer hospitalId;
    private String title;
    private String content;
    private String name;

    public static ReviewResponse of(Integer id, Integer hospitalId, String title, String content, String name){
        return new ReviewResponse(id,hospitalId,title,content,name);
    }
}
