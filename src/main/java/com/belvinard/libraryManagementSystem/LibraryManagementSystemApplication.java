package com.belvinard.libraryManagementSystem;

import com.belvinard.libraryManagementSystem.console.ConsoleHandler;
import com.belvinard.libraryManagementSystem.data.LibraryData;
import com.belvinard.libraryManagementSystem.service.BookService;

public class LibraryManagementSystemApplication {
    public static void main(String[] args) {
        LibraryData libraryData = new LibraryData();
        BookService bookService = new BookService(libraryData);

        ConsoleHandler consoleHandler = new ConsoleHandler(bookService);
        consoleHandler.start();
    }



}
