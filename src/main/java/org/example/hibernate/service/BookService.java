package org.example.hibernate.service;

import org.example.hibernate.entity.Book;
import org.example.hibernate.exception.DuplicateException;
import org.example.hibernate.exception.NotExistException;

public interface BookService {
    void delete(Book book) throws NotExistException;

    void update(Book book) throws DuplicateException, NotExistException;

    Integer create(Book book) throws DuplicateException;

    Book get(Integer id);

    Book getByBookName(String bookName);
}
