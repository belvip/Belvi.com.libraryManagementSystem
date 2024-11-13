package com.belvinard.libraryManagementSystem;

import com.belvinard.libraryManagementSystem.console.ConsoleHandler;
import com.belvinard.libraryManagementSystem.data.LibraryData;
import com.belvinard.libraryManagementSystem.service.BookService;

/**
 * The main entry point of the Library Management System application.
 * This class initializes necessary components and starts the user interaction.
 */
public class LibraryManagementSystemApplication {

    /**
     * The main method where the application execution starts.
     * Initializes required services and starts the console-based interaction.
     */
    public static void main(String[] args) {
        // Initialize LibraryData to manage the book collection
        LibraryData libraryData = new LibraryData();

        // Create a BookService that interacts with the LibraryData
        BookService bookService = new BookService(libraryData);

        // Create a ConsoleHandler for handling user input and interacting with the system
        ConsoleHandler consoleHandler = new ConsoleHandler(bookService);

        // Start the console-based user interaction
        consoleHandler.start();
    }
}
