package com.jpa.study_springboot_jpa.book.domain.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Author {
    @Id
    private Integer id;

    private String name;
}
