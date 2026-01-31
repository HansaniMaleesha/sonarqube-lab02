package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserService {

    // SECURITY ISSUE: Hardcoded credentials
    private String password = "admin123";

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    // VULNERABILITY: SQL Injection
    public void findUser(String username) throws SQLException {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db", "root", password);
             PreparedStatement ps = conn.prepareStatement("SELECT id, name FROM users WHERE name = ?")) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                // process result set if needed
            }
        }
    }

    // SMELL: Unused method
    public void notUsed() {
        LOGGER.info("I am never called");
    }

    // EVEN WORSE: another SQL injection
    public void deleteUser(String username) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db", "root", password);
             PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE name = ?")) {

            ps.setString(1, username);
            ps.executeUpdate();
        }
    }
}
