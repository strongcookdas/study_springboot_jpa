package com.jpa.study_springboot_jpa.service.books;

import com.jpa.study_springboot_jpa.domain.dto.books.BooksResponse;
import com.jpa.study_springboot_jpa.domain.entity.books.Author;
import com.jpa.study_springboot_jpa.domain.entity.books.Book;
import com.jpa.study_springboot_jpa.repository.books.AuthorRepository;
import com.jpa.study_springboot_jpa.repository.books.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
