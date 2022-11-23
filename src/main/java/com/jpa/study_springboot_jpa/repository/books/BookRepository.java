package com.jpa.study_springboot_jpa.repository.books;

import com.jpa.study_springboot_jpa.domain.entity.books.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {

}
