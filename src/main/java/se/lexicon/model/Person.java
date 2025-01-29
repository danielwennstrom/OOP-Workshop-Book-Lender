package se.lexicon.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Person model with properties and methods
 * to manage personal details and interactions with the library system.
 */
public class Person {
    private static int sequencer = 0;
    private final int id;
    private String firstName;
    private String lastName;
    private List<Book> landedBooks;

    public Person(String firstName, String lastName) {
        this.id = getNextId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.landedBooks = new ArrayList<>();
    }

    private static int getNextId() {
        return ++sequencer;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Book> getLandedBooks() {
        return landedBooks;
    }

    public void loanBook(Book book) {
        if (book == null || !book.isAvailable()) {
            System.out.printf("%s %s cannot loan the book (%s) because it's either not available or already loaned.%n", firstName, lastName, book.getTitle());
            return;
        } else if (landedBooks.contains(book)) {
            System.out.printf("%s %s cannot loan the book (%s) because they've already borrowed it.%n", firstName, lastName, book.getTitle());
            return;
        }

        book.setAvailable(false);
        book.setBorrower(this);
        landedBooks.add(book);
        System.out.printf("Book (%s) has been loaned to %s %s.%n", book.getTitle(), firstName, lastName);
    }

    public void returnBook(Book book) {
        if (!landedBooks.contains(book)) {
            System.out.printf("%s %s cannot return the book (%s) because it's not on their list of landed books.%n", firstName, lastName, book.getTitle());
            return;
        }

        book.setAvailable(true);
        book.setBorrower(null);
        landedBooks.remove(book);
        System.out.printf("%s %s has returned the book (%s).%n", firstName, lastName, book.getTitle());
    }

    public String getPersonInformation() {
        StringBuilder sb = new StringBuilder();
        sb.append("Person ID: ").append(getId()).append(", First Name: ").append(getFirstName()).append(", Last Name: ").append(getLastName()).append(", Landed books: [");

        for (int i = 0; i < landedBooks.size(); i++) {
            Book book = landedBooks.get(i);
            sb.append(book.getId()).append(" - ").append(book.getTitle());

            if (i < landedBooks.size() - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");

        return sb.toString();
    }
}