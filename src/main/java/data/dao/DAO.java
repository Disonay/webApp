package data.dao;

import data.entities.HouseEntity;
import data.entities.PersonEntity;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> extends AutoCloseable {
    T save(T object) throws SQLException;
    T findById(Integer id) throws SQLException;
    List<T> getAll() throws SQLException;
    @Override
    default void close() throws Exception {}
}
