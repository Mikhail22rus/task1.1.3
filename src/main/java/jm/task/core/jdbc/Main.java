package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        System.out.println("Таблица User успешно создана.");


        userService.saveUser("Петя", "Петров", (byte) 30);
        System.out.println("User с именем Петя добавлен в базу данных.");
        userService.saveUser("Маша", "Васильева", (byte) 25);
        System.out.println("User с именем Маша добавлен в базу данных.");
        userService.saveUser("Миша", "Толстых", (byte) 40);
        System.out.println("User с именем Миша добавлен в базу данных.");
        userService.saveUser("Сергей", "Иванов", (byte) 28);
        System.out.println("User с именем Сергей добавлен в базу данных.");


        List<User> allUsers = userService.getAllUsers();
        System.out.println("пользователи из базы данных:");
        for (User user : allUsers) {
            System.out.println(user);
        }


        userService.cleanUsersTable();
        System.out.println("Таблица успешно очищена.");


        userService.dropUsersTable();
        System.out.println("Таблица успешно удалена.");
    }
}

