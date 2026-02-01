package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws Exception {
        Calculator calc = new Calculator();
        LOGGER.log(Level.INFO, "Calculation result: {0}", calc.calculate(10, 5, "add"));

        if (args.length == 0) {
            LOGGER.warning("Username not provided");
            return;
        }

        // Call a method for DB operations
        connectAndFindUser(args[0]);
    }

    // Extracted method for testing
    public static boolean connectAndFindUser(String username) {
        String dbUser = System.getenv("DB_USER");
        String dbPass = System.getenv("DB_PASS");

        if (dbUser == null || dbPass == null) {
            LOGGER.severe("Database credentials not set in environment variables.");
            return false;
        }

        Properties props = new Properties();
        props.put("user", dbUser);
        props.put("password", dbPass);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db", props)) {
            UserService service = new UserService(conn);
            service.findUser(username);
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Database connection or query failed", e);
            return false;
        }
    }
}
