package com.belvinard.libraryManagementSystem.data;

import com.belvinard.libraryManagementSystem.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Data access class for managing the book collection.
 * Acts as a simple in-memory storage for books in the library.
 */
@Component
public class LibraryData {

    // List to store Book objects as an in-memory collection
    private final ArrayList<Book> bookCollection = new ArrayList<>();

    /**
     * Adds a new book to the book collection.
     *
     * @param book The Book object to add to the collection.
     */
    public void addBook(Book book) {
        // bookCollection.add(book);  // Add book to in-memory collection
        if (book != null) {
            bookCollection.add(book);
        }
    }

    // Update a book
    public void updateBook(String isbn, Book updatedBook){
        // Check if the updated book is null
        if(updatedBook == null){
            throw new IllegalArgumentException("Updated book cannot be null.");
        }

        // Check if the ISBN is valid
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN must not be null or empty.");
        }


        // Iterate through the collection to find the book by ISBN
        for (int i = 0; i < bookCollection.size(); i++) {
            Book currentBook = bookCollection.get(i);

            // Check if the ISBN matches the one we're looking for
            if(currentBook.getISBN().equals(isbn)){
                //update the nook properties
                currentBook.setTitle(updatedBook.getTitle());
                currentBook.setGenre(updatedBook.getGenre());
                currentBook.setPublicationYear(updatedBook.getPublicationYear());
                currentBook.setISBN(updatedBook.getISBN());
                currentBook.setISBN(updatedBook.getISBN());
            }

            System.out.println("Book updated successfully.");
            return;  // Exit after updating

        }

        // If no matching book is found
        System.out.println("No book found with the provided ISBN.");
    }

    // Method to check if a book exists by ISBN
    public boolean bookExists(String isbn) {
        for (Book book : bookCollection) {
            if (book.getISBN().equals(isbn)) {
                return true;
            }
        }
        return false;
    }


    public List<Book> getBookCollection() {
        return bookCollection;
    }
}
