package repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Repository extends AutoCloseable {
    User save(User user) throws SQLException;
    User get(Integer id) throws SQLException;
    List<User> getAll() throws SQLException;
}
