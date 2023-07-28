package org.example.hibernate.service;

import org.example.hibernate.entity.Book;
import org.example.hibernate.exception.DuplicateException;
import org.example.hibernate.exception.NotExistException;
import org.example.hibernate.repository.BookRepository;

public class BookServiceImpl implements BookService{
    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public void delete(Book book) throws NotExistException {
        if (get(book.getId()) == null)
            throw new NotExistException("Try delete not exist book");

        repository.delete(book);
    }

    @Override
    public void update(Book book) throws DuplicateException, NotExistException {
        Book bookFromDb = get(book.getId());

        if (bookFromDb == null)
            throw new NotExistException("Try update not exist book");

        Book bookFromBookName = getByBookName(book.getTitle());

        if (bookFromBookName != null && bookFromBookName.getId() != book.getId())
            throw new DuplicateException("Duplicate Book by bookName: " + book.getTitle());

        repository.update(book);
    }

    @Override
    public Integer create(Book book) throws DuplicateException {
        if (getByBookName(book.getTitle()) != null)
            throw new DuplicateException("Duplicate Book with BookName: " + book.getTitle());

        return repository.create(book);
    }

    @Override
    public Book getByBookName(String bookName) {
        return repository.getByBookName(bookName);
    }

    @Override
    public Book get(Integer id) {
        return repository.get(id);
    }

}
