package com.jpa.study_springboot_jpa.domain.entity.hospital;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nation_wide_hospitals")
@Getter
public class Hospital {
    @Id
    private Integer id;

    private String hospitalName;
    private String roadNameAddress;

}
