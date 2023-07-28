package org.example.hibernate.service;

import org.example.hibernate.AbstractHibernateTest;
import org.example.hibernate.entity.Book;
import org.example.hibernate.exception.DuplicateException;
import org.example.hibernate.exception.NotExistException;
import org.example.hibernate.repository.BookRepositoryImpl;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class BookServiceImplTest extends AbstractHibernateTest {

    private BookService service;

    @Override
    public void setUp() {
        super.setUp();
        service = new BookServiceImpl(new BookRepositoryImpl());
    }

    @Override
    public void tearDown() {
        super.tearDown();
    }

    @Test
    public void deleteBook() throws NotExistException, DuplicateException {
        Book forDeleteBook = getBookByStaticValues();
        int id = service.create(forDeleteBook);

        flushAndClearSession();

        service.delete(forDeleteBook);

        flushAndClearSession();

        Book bookFromDb = service.get(id);
        assertThat(bookFromDb, equalTo(null));
    }

    @Test
    public void updateBook() throws DuplicateException, NotExistException {
        Book book = getBookByStaticValues();
        int idBookForUpdate = service.create(book);

        flushAndClearSession();

        Book expectedBook = getBookByStaticValues();

        expectedBook.setId(idBookForUpdate);
        service.update(expectedBook);

        flushAndClearSession();

        Book actualBook = service.get(idBookForUpdate);
        assertThat(actualBook, equalTo(expectedBook));
    }

    @Test
    public void createBook() throws DuplicateException {
        Book expectedBook = getBookByStaticValues();
        service.create(expectedBook);

        flushAndClearSession();

        Book bookFromDb = service.getByBookName(expectedBook.getTitle());
        assertThat(bookFromDb, equalTo(expectedBook));
    }

    @Test
    public void getBook() throws DuplicateException {
        Book book = getBookByStaticValues();
        int id = service.create(book);

        flushAndClearSession();

        Book bookFromDb = service.get(id);
        assertThat(bookFromDb, equalTo(book));
    }

    @Test
    public void getBookByBookName() throws DuplicateException {
        Book expectedBook = getBookByStaticValues();
        service.create(expectedBook);

        flushAndClearSession();

        Book actualBook = service.getByBookName(expectedBook.getTitle());
        assertThat(actualBook, equalTo(expectedBook));
    }

    private Book getBookByStaticValues() {
        String title = "Java Persistence with Hibernate";
        String author = "Christian Bauer";
        double price = 55d;
        return new Book(title,author, price);
    }
}
