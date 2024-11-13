package com.belvinard.libraryManagementSystem.model;

import lombok.*;

import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Book {
    private String title;
    private String author;
    private String genre;
    private String ISBN;
    private int publicationYear;

    // Constructor that will call setters with validation
    public Book(String title, String author, String genre, String ISBN, int publicationYear) {
        setTitle(title);
        setAuthor(author);
        setGenre(genre);
        setISBN(ISBN);
        setPublicationYear(publicationYear);
    }

    // Title Validation
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty() || title.length() < 3) {
            throw new IllegalArgumentException("Invalid title. It must be at least 3 characters long and not empty.");
        }
        if (!Pattern.matches("^[a-zA-Z0-9\\s]+$", title)) {
            throw new IllegalArgumentException("Invalid title format. Only alphabetic characters, numbers, and spaces are allowed.");
        }
        this.title = title;
    }

    // Author Validation
    public void setAuthor(String author) {
        if (author == null || !Pattern.matches("^[a-zA-Z]+([\\s][a-zA-Z]+)*$", author)) {
            throw new IllegalArgumentException("Invalid author format. Only alphabetic characters and space are allowed.");
        }
        this.author = author;
    }

    // ISBN Validation
    public void setISBN(String ISBN) {
        if (ISBN == null || !Pattern.matches("\\d{5}", ISBN)) {
            throw new IllegalArgumentException("Invalid ISBN format. Must be 5 digits.");
        }
        this.ISBN = ISBN;
    }

    // Publication Year Validation
    public void setPublicationYear(int publicationYear) {
        int currentYear = java.time.Year.now().getValue();
        if (publicationYear < 1000 || publicationYear > currentYear) {
            throw new IllegalArgumentException("Invalid publication year. It must be between 1000 and the current year.");
        }
        this.publicationYear = publicationYear;
    }

    // Genre Validation
    public void setGenre(String genre) {
        Set<String> allowedGenres = new HashSet<>(Arrays.asList("Fiction", "Non-Fiction", "Fantasy", "Science Fiction", "Biography", "History"));
        if (genre == null || !allowedGenres.contains(genre.trim())) {
            throw new IllegalArgumentException("Invalid genre. It must be one of: Fiction, Non-Fiction, Fantasy, Science Fiction, Biography, History.");
        }
        this.genre = genre;
    }
}
