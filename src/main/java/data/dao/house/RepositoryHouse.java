package data.dao.house;

import data.entities.HouseEntity;
import data.entities.PersonEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public interface RepositoryHouse extends AutoCloseable {
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
    HouseEntity save(HouseEntity house) throws SQLException;
    HouseEntity findById(Integer id) throws SQLException;
    List<HouseEntity> getAll() throws SQLException;

    void addPersonToHouse(int houseId, PersonEntity person);
    @Override
    default void close() throws Exception {};
}
