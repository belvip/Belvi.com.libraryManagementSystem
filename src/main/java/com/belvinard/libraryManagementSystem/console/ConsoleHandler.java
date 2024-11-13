package com.belvinard.libraryManagementSystem.console;

import com.belvinard.libraryManagementSystem.model.Book;
import com.belvinard.libraryManagementSystem.service.BookService;

import java.util.Scanner;

public class ConsoleHandler {

    private final BookService bookService;
    private final Scanner scanner;

    public ConsoleHandler(BookService bookService) {
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);

    }

    public void start() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2 :
                    running = false;
                    System.out.println("Exiting the system ...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu(){
        System.out.println("\n================== Library Management System================== ");
        System.out.println("1. Add Book");
        System.out.println("2. Exit");
        System.out.print("Enter your choice :  ");
    }

    private void addBook(){
        System.out.print("Enter book title : ");
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
    }

}
