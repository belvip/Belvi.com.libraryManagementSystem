package com.belvinard.libraryManagementSystem.config;

import com.belvinard.libraryManagementSystem.data.LibraryData;
import com.belvinard.libraryManagementSystem.service.BookService;
import org.springframework.context.annotation.Bean;

public class LibraryConfig {

    @Bean
    public LibraryData libraryData() {
        return new LibraryData();
    }

    @Bean
    public BookService bookService() {
        return new BookService(libraryData());
    }
}
