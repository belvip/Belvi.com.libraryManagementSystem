package com.belvinard.libraryManagementSystem.data;

import com.belvinard.libraryManagementSystem.model.Book;

import java.util.ArrayList;

public class LibraryData {
    private ArrayList<Book> bookCollection = new ArrayList<>();

    public void addBook(Book book){
        bookCollection.add(book);
    }
}
