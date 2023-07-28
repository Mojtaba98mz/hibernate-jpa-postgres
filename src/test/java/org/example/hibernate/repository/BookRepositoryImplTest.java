package org.example.hibernate.repository;

import org.example.hibernate.AbstractHibernateTest;
import org.example.hibernate.entity.Book;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class BookRepositoryImplTest extends AbstractHibernateTest {

    private BookRepository repository;

    @Override
    public void setUp() {
        super.setUp();
        repository = new BookRepositoryImpl();
    }

    @Override
    public void tearDown() {
        super.tearDown();
    }

    @Test
    public void deleteBook() {
        Book forDeleteBook = getBookByStaticValues();
        int id = repository.create(forDeleteBook);

        flushAndClearSession();

        repository.delete(forDeleteBook);

        flushAndClearSession();

        Book bookFromDb = repository.get(id);

        assertThat(bookFromDb, equalTo(null));
    }

    @Test
    public void updateBook() {
        Book book = getBookByStaticValues();
        int idBookForUpdate = repository.create(book);

        flushAndClearSession();

        String title = "second";
        String author = "second";
        double price = 55d;

        Book expectedBook = new Book(title,author,price);
        expectedBook.setId(idBookForUpdate);
        repository.update(expectedBook);

        flushAndClearSession();

        Book actualBook = repository.get(idBookForUpdate);
        assertThat(actualBook, equalTo(expectedBook));
    }

    @Test
    public void createBook() {
        String title = "second";
        String author = "second";
        double price = 55d;

        Book expectedBook = new Book(title,author,price);
        repository.create(expectedBook);

        flushAndClearSession();

        Book bookFromDb = repository.getByBookName(title);
        assertThat(bookFromDb, equalTo(expectedBook));
    }

    @Test
    public void getBookByTitle() {
        String title = "second";
        String author = "second";
        double price = 55d;

        Book expectedBook = new Book(title,author,price);
        repository.create(expectedBook);

        flushAndClearSession();

        Book actualBook = repository.getByBookName(title);
        assertThat(actualBook, equalTo(expectedBook));
    }

    @Test
    public void getBook() {
        Book book = getBookByStaticValues();
        int id = repository.create(book);

        flushAndClearSession();

        Book bookFromDb = repository.get(id);
        assertThat(bookFromDb, equalTo(book));
    }

    private Book getBookByStaticValues() {
        String title = "Java Persistence with Hibernate";
        String author = "Christian Bauer";
        double price = 55d;
        return new Book(title,author, price);
    }
}
