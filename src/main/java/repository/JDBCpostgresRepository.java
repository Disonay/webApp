package repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCpostgresRepository implements Repository {
    private final String URL = "jdbc:postgresql://localhost:5432/traineedb";
    private final String USER = "sergei";
    private final String PASS = "admin";
    private final Properties info;
    private final Connection connection;
    private final Statement statement;

    public JDBCpostgresRepository() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        info = new Properties();
        info.setProperty("user", USER);
        info.setProperty("password", PASS);

        connection = DriverManager.getConnection(URL, info);
        statement = connection.createStatement();
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String middleName = resultSet.getString("middle_name");

        return new User(id, name, surname, middleName);
    }

    @Override
    public User save(User user) throws SQLException {
        statement.executeUpdate("INSERT INTO users (name, surname, middle_name) VALUES (" +
                "'" + user.getName() + "'," + "'" + user.getSurname() + "'," + "'" + user.getMiddleName() + "')");

        ResultSet resultSet =  statement.executeQuery("SELECT * FROM users ORDER BY id LIMIT 1");
        resultSet.next();

        return getUserFromResultSet(resultSet);

    }

    @Override
    public User get(Integer id) throws SQLException {
        String query = "SELECT id, name, surname, middle_name FROM users WHERE id=" + id + " LIMIT 1";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();

        return getUserFromResultSet(resultSet);
    }

    @Override
    public List<User> getAll() throws SQLException {
        String query = "SELECT * FROM users";
        ResultSet resultSet = statement.executeQuery(query);
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(getUserFromResultSet(resultSet));
        }

        return users;
    }

    @Override
    public void close() throws Exception {
        statement.close();
        connection.close();
    }
}
