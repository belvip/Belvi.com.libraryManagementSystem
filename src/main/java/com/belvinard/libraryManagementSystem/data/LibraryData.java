package com.belvinard.libraryManagementSystem.data;

import com.belvinard.libraryManagementSystem.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Data access class for managing the book collection.
 * Acts as a simple in-memory storage for books in the library.
 */
@Component
public class LibraryData {

    // List to store Book objects as an in-memory collection
    private ArrayList<Book> bookCollection = new ArrayList<>();

    /**
     * Adds a new book to the book collection.
     *
     * @param book The Book object to add to the collection.
     */
    public void addBook(Book book) {
        bookCollection.add(book);  // Add book to in-memory collection
    }

    // Additional methods like findBook, removeBook, or getBookCollection could be added as needed
}
