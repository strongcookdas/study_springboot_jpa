package com.jpa.study_springboot_jpa.domain.dto.books;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BooksResponse {
    private Integer bookId;
    private String bookName;
    private String bookAuthorName;

    public static BooksResponse of(Integer bookId, String bookName, String bookAuthorName){
        return new BooksResponse(bookId,bookName,bookAuthorName);
    }
}
