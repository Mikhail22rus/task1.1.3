package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.util.Util;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Util util;


    public UserDaoJDBCImpl() {
        this.util = new Util();
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(255) NOT NULL," +
                "lastName VARCHAR(255) NOT NULL," +
                "age TINYINT NOT NULL)";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица users успешно создана (если ее не было).");
        } catch (SQLException e) {
            System.err.println("Ошибка при создании таблицы: " + e.getMessage());
        }
    }


    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица users успешно удалена (если существовала).");
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении таблицы: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь " + name + " " + lastName + " успешно сохранен.");
        } catch (SQLException e) {
            System.err.println("Ошибка при сохранении пользователя: " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь с ID " + id + " успешно удален.");
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении пользователя: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM users";
        try (
                Connection connection = util.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении пользователей: " + e.getMessage());
            return null;
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица users успешно очищена.");
        } catch (SQLException e) {
            System.err.println("Ошибка при очистке таблицы: " + e.getMessage());
        }
    }
}
