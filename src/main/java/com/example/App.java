package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Logger;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws Exception {
        Calculator calc = new Calculator();
        LOGGER.info(String.valueOf(calc.calculate(10, 5, "add")));

        Properties props = new Properties();
        props.put("user", "root");
        props.put("password", "secret");

        try (Connection conn =
                 DriverManager.getConnection("jdbc:mysql://localhost/db", props)) {

            UserService service = new UserService(conn);
            service.findUser("admin");
        }
    }
}
