package com.belvinard.libraryManagementSystem.service;

import com.belvinard.libraryManagementSystem.data.LibraryData;
import com.belvinard.libraryManagementSystem.model.Book;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing book-related operations.
 * This class serves as an intermediary layer between
 * the data layer and the rest of the application.
 */
@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    // Dependency on LibraryData, which manages data storage for books
    private final LibraryData libraryData;

    /**
     * Constructor-based dependency injection.
     * The libraryData dependency is automatically injected by Spring.
     *
     * @param libraryData A LibraryData object to handle book storage and retrieval.
     */
    @Autowired
    public BookService(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

    /**
     * Adds a new book to the library.
     *
     * @param book The Book object to add to the library.
     */
    public void addBook(Book book) {

        libraryData.addBook(book);  // Delegate book addition to the data layer

    }
}
