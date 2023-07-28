package org.example.hibernate.repository;


import org.example.hibernate.utility.SessionHolder;
import org.hibernate.Session;

public abstract class AbstractRepository {

    public void flush() {
        getSession().flush();
    }

    protected Session getSession() {
        return SessionHolder.get();
    }
}