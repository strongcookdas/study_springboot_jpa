package com.jpa.study_springboot_jpa.domain.entity.hospital;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nation_wide_hospitals")
@Getter
public class Hospital {
    @Id
    private Integer id;

    private String hospitalName;
    private String roadNameAddress;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    private List<Review> reviews;
}
