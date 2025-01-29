package se.lexicon;


import se.lexicon.model.Book;
import se.lexicon.model.Person;

public class App {
    public static void main(String[] args) {
        Person person = new Person("Sven", "Svensson");
        Person person2 = new Person("Erik", "Eriksson");

        Book book1 = new Book("The Three-Body Problem", "Cixin Liu", person);
        person.loanBook(book1);

        Book book2 = new Book("The Dark Forest", "Cixin Liu");
        person.loanBook(book2);

        person2.loanBook(book1);
        person.returnBook(book1);
        person2.loanBook(book1);

        Book book3 = new Book("Death's End", "Cixin Liu", person);

        System.out.println(person.getPersonInformation());
        System.out.println(person2.getPersonInformation());
    }
}
