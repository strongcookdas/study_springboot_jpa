package com.jpa.study_springboot_jpa.domain.dto.books;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BooksResponse {
    private Integer bookId;
    private String bookName;
    private String bookAuthorName;
    private String publisherName;

    public static BooksResponse of(Integer bookId, String bookName, String bookAuthorName, String publisherName){
        return new BooksResponse(bookId,bookName,bookAuthorName,publisherName);
    }
}
