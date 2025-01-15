package dao;

import dao.*;
import java.util.List;


public interface DataAccessObject<T> {
    void insert(T entity);
    void update(T entity);
    void delete(int id);
    T select(int id);
    List<T> selectAll();
}
