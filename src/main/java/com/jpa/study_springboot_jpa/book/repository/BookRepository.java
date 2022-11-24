package com.jpa.study_springboot_jpa.book.repository;

import com.jpa.study_springboot_jpa.book.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {

}
