package com.jpa.study_springboot_jpa.book.service;

import com.jpa.study_springboot_jpa.book.domain.dto.BooksResponse;
import com.jpa.study_springboot_jpa.book.domain.entity.Book;
import com.jpa.study_springboot_jpa.book.repository.AuthorRepository;
import com.jpa.study_springboot_jpa.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<BooksResponse> getBooks(){
        List<Book> books = bookRepository.findAll();
        List<BooksResponse> list = new ArrayList<>();
        for (Book book: books) {
            BooksResponse booksResponse = BooksResponse.of(book.getId(), book.getName(),book.getAuthor().getName(), book.getPublisher().getName());
            list.add(booksResponse);
        }
        return list;
    }

}
