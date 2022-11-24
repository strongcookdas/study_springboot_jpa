package com.jpa.study_springboot_jpa.book.repository;

import com.jpa.study_springboot_jpa.book.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
