package org.example.hibernate.repository;

import org.example.hibernate.entity.Book;
import org.hibernate.query.Query;

public class BookRepositoryImpl extends AbstractRepository implements BookRepository{
    @Override
    public void delete(Book book) {
        Book persistentInstance = get(book.getId());
        getSession().delete(persistentInstance);
    }

    @Override
    public void update(Book book) {
        getSession().merge(book);
    }

    @Override
    public Integer create(Book book) {
        return (Integer) getSession().save(book);
    }

    @Override
    public Book getByBookName(String bookName) {
        Query query = getSession().createQuery("from Book where title = :bookName");
        query.setParameter("bookName", bookName);
        return (Book) query.uniqueResult();
    }


    @Override
    public Book get(Integer id) {
        return getSession().get(Book.class, id);
    }
}
