package com.jpa.study_springboot_jpa.controller.book;

import com.jpa.study_springboot_jpa.domain.dto.books.BooksResponse;
import com.jpa.study_springboot_jpa.service.books.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @GetMapping("/list")
    public ResponseEntity<List<BooksResponse>> getBooks(){
        return ResponseEntity.ok().body(bookService.getBooks());
    }
}
