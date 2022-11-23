package com.jpa.study_springboot_jpa.domain.entity.books;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Book {
    @Id
    private Integer id;

    private String name;
    private Integer publisherId;
    private Integer authorId;

}
