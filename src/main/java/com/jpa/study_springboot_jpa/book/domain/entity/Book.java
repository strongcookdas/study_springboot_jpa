package com.jpa.study_springboot_jpa.book.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Book {
    @Id
    private Integer id;

    private String name;

    @OneToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

}
