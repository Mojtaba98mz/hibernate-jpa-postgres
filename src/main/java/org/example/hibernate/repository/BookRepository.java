package org.example.hibernate.repository;

import org.example.hibernate.entity.Book;

public interface BookRepository {
    void delete(Book book);

    void update(Book book);

    Integer create(Book book);

    Book get(Integer id);

    Book getByBookName(String bookName);

}
