package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private final String DB_URL = "jdbc:mysql://localhost:3306/myDB";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "root";


    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Соединение с базой данных успешно установлено.");
        } catch (SQLException e) {
            System.err.println("Ошибка при подключении к базе данных: " + e.getMessage());
            connection = null;
        }
        return connection;
    }

}
