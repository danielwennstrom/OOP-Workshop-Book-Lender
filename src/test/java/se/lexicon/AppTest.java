package se.lexicon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.lexicon.model.Person;
import se.lexicon.model.Book;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private Person person;
    private Book book;

    @BeforeEach
    public void setUp() {
        person = new Person("Sven", "Svensson");
        book = new Book("The Gunslinger", "Stephen King");
    }

    @Test
    @DisplayName("Loaning a book successfully")
    public void testLoanBook() {
        person.loanBook(book);
        assertFalse(book.isAvailable());
        assertEquals(person, book.getBorrower());
    }

    @Test
    @DisplayName("Loaning a null book throws NullPointerException")
    public void testLoanNullBook() {
        assertThrows(NullPointerException.class, () -> person.loanBook(null));
    }

    @Test
    @DisplayName("Returning a borrowed book successfully")
    public void testReturnBook() {
        person.loanBook(book);
        person.returnBook(book);
        assertTrue(book.isAvailable());
        assertNull(book.getBorrower());
    }

    @Test
    @DisplayName("Initializing a book with valid title and author")
    public void testInitWithValidTitleAndAuthor() {
        assertNotNull(book);
        assertEquals("The Gunslinger", book.getTitle());
        assertEquals("Stephen King", book.getAuthor());
        assertTrue(book.isAvailable());
    }

    @Test
    @DisplayName("Initializing a book without title or author")
    public void testInitWithoutTitleOrAuthor() {
        Book nullBook = new Book(null, null);
        assertNull(nullBook.getTitle());
        assertNull(nullBook.getAuthor());
    }

    @Test
    @DisplayName("Setting the borrower of a book")
    public void testSetBorrower() {
        book.setBorrower(person);
        assertTrue(book.isAvailable());
        assertEquals(person, book.getBorrower());
    }

    @Test
    @DisplayName("Setting null as the borrower of a book")
    public void testSetNullBorrower() {
        book.setBorrower(null);
        assertNull(book.getBorrower());
    }
}