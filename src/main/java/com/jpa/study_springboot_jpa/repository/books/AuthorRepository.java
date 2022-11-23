package com.jpa.study_springboot_jpa.repository.books;

import com.jpa.study_springboot_jpa.domain.entity.books.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
