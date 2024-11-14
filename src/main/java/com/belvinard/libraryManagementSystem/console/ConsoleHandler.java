package com.belvinard.libraryManagementSystem.console;

import com.belvinard.libraryManagementSystem.model.Book;
import com.belvinard.libraryManagementSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;

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
        // Initialize LibraryData and pass it to BookService
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);

        // Add some sample books to libraryData for testing
        /*libraryData.addBook(new Book("Java Basics", "Author 1", "Java", "12345", 2020));
        libraryData.addBook(new Book("Advanced Java", "Author 2", "Java", "67890", 2021)); */
    }

    /**
     * Displays the main menu of the library management system.
     * Shows options for the user to choose from.
     */
    private void displayMenu() {
        System.out.println("\n================== Library Management System ==================");
        System.out.println("1. Add Book");
        System.out.println("2. Update Book");
        System.out.println("3. Exit");
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
                    updateBook();
                case 3:
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

    public void updateBook() {
        System.out.print("Enter ISBN of the book to update: ");
        String isbn = scanner.nextLine().trim();

        // Validate ISBN input
        if (isbn.isEmpty() || !isbn.matches("\\d{5}")) {  // Assuming 5-digit ISBN format
            System.out.println("Invalid ISBN format. ISBN must be 5 digits.");
            return;
        }

        // Check if the book exists
        if (!bookService.bookExists(isbn)) {
            System.out.println("No book found with the provided ISBN.");
            return;
        }


        // Retrieve the existing book to update specific fields
        Book existingBook = bookService.getBookByISBN(isbn);

        boolean updating = true;
        while (updating) {
            System.out.println("\nChoose a field to update:");
            System.out.println("1. Title");
            System.out.println("2. Author");
            System.out.println("3. Genre");
            System.out.println("4. Publication Year");
            System.out.println("5. Finish updating");

            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    // Update title
                    String title = getValidInput("Enter new title (at least 3 characters): ", input -> input.length() >= 3);
                    existingBook.setTitle(title);
                    System.out.println("Title updated.");
                    break;

                case 2:
                    // Update author
                    String author = getValidInput("Enter new author (only letters and spaces): ", input -> input.matches("^[a-zA-Z\\s]+$"));
                    existingBook.setAuthor(author);
                    System.out.println("Author updated.");
                    break;

                case 3:
                    // Update genre
                    String genre = getValidInput("Enter new genre (Data Structures, Software Development, Java, Python, JavaScript, Databases, Software Architecture & Design, Biography, History): ",
                            input -> input.matches("Data Structures|Software Development|Java|Python|JavaScript|Databases|Software Architecture & Design|Biography|History"));
                    existingBook.setGenre(genre);
                    System.out.println("Genre updated.");
                    break;

                case 4:
                    // Update publication year
                    int year;
                    while (true) {
                        System.out.print("Enter new publication year (between 1000 and current year): ");
                        try {
                            year = Integer.parseInt(scanner.nextLine().trim());
                            int currentYear = java.time.Year.now().getValue();
                            if (year < 1000 || year > currentYear) {
                                System.out.println("Invalid year. Must be between 1000 and the current year.");
                            } else {
                                existingBook.setPublicationYear(year);
                                System.out.println("Publication year updated.");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a numeric year.");
                        }
                    }
                    break;

                case 5:
                    // Finish updating
                    updating = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            // Display the updated book information after each update
            displayUpdatedBookInfo(existingBook);
        }

        // Save the updated book details
        bookService.updateBook(isbn, existingBook);
        System.out.println("Book updated successfully.");
    }

    // Helper method to validate user input based on a custom condition
    private String getValidInput(String prompt, Predicate<String> validationCondition) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (validationCondition.test(input)) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
        return input;
    }

    // Helper method to display the updated book information
    private void displayUpdatedBookInfo(Book updatedBook) {
        System.out.println("Updated Book Details:");
        System.out.println("Title: " + updatedBook.getTitle());
        System.out.println("Author: " + updatedBook.getAuthor());
        System.out.println("Genre: " + updatedBook.getGenre());
        System.out.println("ISBN: " + updatedBook.getISBN());
        System.out.println("Publication Year: " + updatedBook.getPublicationYear());
    }


}
