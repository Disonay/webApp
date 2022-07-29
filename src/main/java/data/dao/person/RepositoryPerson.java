package data.dao.person;

import data.entities.PersonEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public interface RepositoryPerson extends AutoCloseable {
    String USERNAME_PROPERTY = "USERNAME";
    String PASSWORD_PROPERTY = "PASSWORD";
    String URL_PROPERTY = "URL";

    default Properties loadProperties() {
        Properties info = new Properties();
        info.setProperty("user",
                Objects.requireNonNull(System.getProperty(USERNAME_PROPERTY), "Username property not set."));
        info.setProperty("password",
                Objects.requireNonNull(System.getProperty(PASSWORD_PROPERTY), "Password property not set."));
        info.setProperty("url",
                Objects.requireNonNull(System.getProperty(URL_PROPERTY), "Url property not set."));
        return info;
    }
    PersonEntity save(PersonEntity person) throws SQLException;
    PersonEntity findById(Integer id) throws SQLException;
    List<PersonEntity> getAll() throws SQLException;
    @Override
    default void close() throws Exception {};
}
