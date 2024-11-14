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

    // Dependency on LibraryData, which manages data storage for books
    private final LibraryData libraryData;
    private Book[] bookCollection;

    /**
     * Constructor-based dependency injection.
     * The libraryData dependency is automatically injected by Spring.
     *
     * @param libraryData A LibraryData object to handle book storage and retrieval.
     */
    @Autowired
    public BookService(LibraryData libraryData) {

        if (libraryData == null) {
            throw new IllegalArgumentException("LibraryData cannot be null.");
        }

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

    public void updateBook(String isbn, Book updatedBook) {
        libraryData.updateBook(isbn, updatedBook);
    }

    /*public boolean bookExists(String isbn) {
        return libraryData.getBookCollection().stream().anyMatch(book -> book.getISBN().equals(isbn));
    }*/

    // Method to check if a book exists by ISBN (delegating to LibraryData)
    public boolean bookExists(String isbn) {
        return libraryData.bookExists(isbn);
    }


    // Method to get a book by ISBN
    public Book getBookByISBN(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN must not be null or empty.");
        }

        // Use libraryData's bookCollection to search for the book
        for (Book book : libraryData.getBookCollection()) {
            if (book.getISBN().equals(isbn)) {
                return book; // Book found
            }
        }

        return null; // Book not found
    }

}
