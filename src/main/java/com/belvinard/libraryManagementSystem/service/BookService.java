package com.belvinard.libraryManagementSystem.service;

import com.belvinard.libraryManagementSystem.data.LibraryData;
import com.belvinard.libraryManagementSystem.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final LibraryData libraryData;

    @Autowired
    public BookService(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

    public void addBook(Book book){
        libraryData.addBook(book);
    }
}
