package data.dao.person;

import data.entities.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPersonDAO implements PersonDAO {
    private final Connection connection;
    public JdbcPersonDAO(@Autowired Connection connection) throws SQLException {
        this.connection = connection;
    }

    private PersonEntity getPersonFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String middleName = resultSet.getString("middle_name");

        return new PersonEntity(id, name, surname, middleName);
    }

    @Override
    public PersonEntity save(PersonEntity person) throws SQLException {
        String insertQuery =
                "INSERT INTO person (name, surname, middle_name) " + "VALUES (" + "'" + person.getName() + "'," + "'" + person.getSurname() + "'," + "'" + person.getMiddleName() + "')";
        try (PreparedStatement ps = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            ps.executeQuery();
            ResultSet rs = ps.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }

            return new PersonEntity(id, person.getName(), person.getSurname(), person.getMiddleName());
        }
    }

    @Override
    public PersonEntity findById(Integer id) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT id, name, surname, middle_name FROM person WHERE id=" + id + " LIMIT 1";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();

            return getPersonFromResultSet(resultSet);
        }
    }

    @Override
    public List<PersonEntity> getAll() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(query);
            List<PersonEntity> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(getPersonFromResultSet(resultSet));
            }

            return users;
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
