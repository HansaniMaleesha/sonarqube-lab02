package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        Calculator calc = new Calculator();

        // Safe logging of calculation result
        LOGGER.log(Level.INFO, "Calculation result: {0}", calc.calculate(10, 5, "add"));

        // Check for username argument
        if (args.length == 0) {
            LOGGER.warning("Username not provided");
            return;
        }

        // Call method for DB operations
        connectAndFindUser(args[0]);
    }

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

        // Connect to DB safely
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
