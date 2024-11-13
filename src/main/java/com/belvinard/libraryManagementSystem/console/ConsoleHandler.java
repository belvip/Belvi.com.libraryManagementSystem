package com.belvinard.libraryManagementSystem.console;

import com.belvinard.libraryManagementSystem.model.Book;
import com.belvinard.libraryManagementSystem.service.BookService;

import java.util.Scanner;

/**
 * Handles user interaction via the console, providing a text-based menu for managing the library.
 * This class allows the user to add books and exit the system.
 */
public class ConsoleHandler {

    // Service for managing books in the library system
    private final BookService bookService;

    // Scanner for reading user input from the console
    private final Scanner scanner;

    /**
     * Constructor for initializing ConsoleHandler with a BookService.
     *
     * @param bookService The BookService instance for managing books.
     */
    public ConsoleHandler(BookService bookService) {
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the console-based interaction for the library management system.
     * This method runs a loop, displaying the menu and processing user choices.
     */
    public void start() {
        boolean running = true;
        while (running) {
            displayMenu();  // Display the available options
            int choice = scanner.nextInt();  // Read user choice
            scanner.nextLine();  // Consume the newline character left by nextInt()

            // Handle user choices
            switch (choice) {
                case 1:
                    addBook();  // Call method to add a book
                    break;
                case 2:
                    running = false;  // Exit the loop and terminate the program
                    System.out.println("Exiting the system ...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Displays the main menu of the library management system.
     * Shows options for the user to choose from.
     */
    private void displayMenu() {
        System.out.println("\n================== Library Management System ==================");
        System.out.println("1. Add Book");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Prompts the user for information about a book and adds it to the library system.
     * Collects input such as title, author, genre, ISBN, and publication year.
     */
    private void addBook() {
        // Collect book information from the user
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter publication year: ");
        int year = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character after reading an integer

        // Create a new Book object with the user input
        Book book = new Book(title, author, genre, isbn, year);

        // Add the book to the library system via BookService
        bookService.addBook(book);

        // Notify the user that the book has been added
        System.out.println("Book added successfully.");
    }
}
