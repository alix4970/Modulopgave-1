package com.modulopgave1.dal;

import java.util.List;

public interface IRepository<T> {
    public T create(T entity);
    public T read(int id);
    public List<T> read(T entity);
    public boolean update(T entity);
    public void delete(T entity);
}
