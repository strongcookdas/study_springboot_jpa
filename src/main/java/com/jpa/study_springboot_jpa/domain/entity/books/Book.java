package com.jpa.study_springboot_jpa.domain.entity.books;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
public class Book {
    @Id
    private Integer id;

    private String name;
    private Integer publisherId;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

}
