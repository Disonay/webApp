package repository.person;

import repository.entities.PersonEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class JdbcPostgresRepositoryPerson implements RepositoryPerson {
    private final Connection connection;
    private final Properties info;

    public JdbcPostgresRepositoryPerson() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        this.info = loadProperties();
        String URL = (String) info.get("url");
        connection = DriverManager.getConnection(URL, info);
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
    public PersonEntity get(Integer id) throws SQLException {
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
