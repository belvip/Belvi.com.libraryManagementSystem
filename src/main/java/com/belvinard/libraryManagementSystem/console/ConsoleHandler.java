package com.belvinard.libraryManagementSystem.console;

import com.belvinard.libraryManagementSystem.model.Book;
import com.belvinard.libraryManagementSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.InputMismatchException;
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
    @Autowired
    public ConsoleHandler(BookService bookService) {
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);
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
     * Starts the console-based interaction for the library management system.
     * This method runs a loop, displaying the menu and processing user choices.
     */
    public void start() {

        boolean running = true;

        while (running) {
            displayMenu();  // Display the available options
            int choice = -1;

            // Handling invalid input for menu choice
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character left by nextInt()
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
                continue; // Go back to the start of the loop to prompt the user again
            }

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
     * Prompts the user for information about a book and adds it to the library system.
     * Collects input such as title, author, genre, ISBN, and publication year.
     */
    private void addBook() {
        try {
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
            scanner.nextLine();  // Consume newline

            Book book = new Book(title, author, genre, isbn, year);
            bookService.addBook(book);
            System.out.println("Book added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
