package repository;

import repository.entities.User;
import repository.entities.UsersEntity;

import java.sql.SQLException;
import java.util.List;

public interface Repository extends AutoCloseable{
    void save(UsersEntity user) throws SQLException;
    UsersEntity get(Integer id) throws SQLException;
    List<UsersEntity> getAll() throws SQLException;
    @Override
    void close() throws Exception;
}
