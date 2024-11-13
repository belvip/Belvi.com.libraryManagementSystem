package com.belvinard.libraryManagementSystem.model;

import lombok.*;

import java.util.Set;
import java.util.regex.Pattern;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Book {

    // Fields representing properties of a book
    private String title;            // Title of the book
    private String author;           // Author of the book
    private String ISBN;             // ISBN number, formatted as a 5-digit string
    private int publicationYear;     // Year the book was published
    private String genre;


    // Set of allowed genres for validation purposes
    private static final Set<String> ALLOWED_GENRES = Set.of(
            "Fiction", "Non-Fiction", "Fantasy", "Science Fiction", "Biography", "History"
    );

    public Book(String title, String author, String genre, String isbn, int year) {

    }

    /**
     * Sets the title of the book after validating its format.
     * Title must be at least 3 characters long, non-empty, and contain
     * only alphabetic characters, numbers, and spaces.
     *
     * @param title The title of the book.
     * @throws IllegalArgumentException if the title is invalid.
     */
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty() || title.length() < 3) {
            throw new IllegalArgumentException("Invalid title. It must be at least 3 characters long and not empty.");
        }
        if (!Pattern.matches("^[a-zA-Z0-9\\s]+$", title)) {
            throw new IllegalArgumentException("Invalid title format. Only alphabetic characters, numbers, and spaces are allowed.");
        }
        this.title = title;
    }

    /**
     * Sets the author of the book after validating its format.
     * The author name must contain only alphabetic characters and spaces.
     *
     * @param author The name of the author.
     * @throws IllegalArgumentException if the author name is invalid.
     */
    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty() || !Pattern.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$", author)) {
            throw new IllegalArgumentException("Invalid author format. Only alphabetic characters and spaces are allowed.");
        }
        this.author = author;
    }

    /**
     * Sets the ISBN number after validating it. ISBN must be exactly 5 digits.
     *
     * @param ISBN The ISBN number of the book.
     * @throws IllegalArgumentException if the ISBN format is invalid.
     */
    public void setISBN(String ISBN) {
        if (ISBN == null || !Pattern.matches("\\d{5}", ISBN)) {
            throw new IllegalArgumentException("Invalid ISBN format. Must be 5 digits.");
        }
        this.ISBN = ISBN;
    }

    /**
     * Sets the publication year of the book. The year must be between
     * 1000 and the current year.
     *
     * @param publicationYear The year the book was published.
     * @throws IllegalArgumentException if the publication year is out of range.
     */
    public void setPublicationYear(int publicationYear) {
        int currentYear = java.time.Year.now().getValue();
        if (publicationYear < 1000 || publicationYear > currentYear) {
            throw new IllegalArgumentException("Invalid publication year. It must be between 1000 and the current year.");
        }
        this.publicationYear = publicationYear;
    }

    /**
     * Sets the genre of the book. The genre must be one of the allowed values
     * (e.g., Fiction, Non-Fiction, etc.).
     *
     * @param genre The genre of the book.
     * @throws IllegalArgumentException if the genre is not allowed.
     */
    public void setGenre(String genre) {
        if (genre == null || !ALLOWED_GENRES.contains(genre)) {
            throw new IllegalArgumentException("Invalid genre. Must be one of: " + ALLOWED_GENRES);
        }
        this.genre = genre;
    }
}
