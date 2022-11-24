package com.jpa.study_springboot_jpa.domain.entity.hospital;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String name;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    @JsonIgnore
    private Hospital hospital;

    public Review(String title, String content, String name, Hospital hospital) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.hospital = hospital;
    }
}
