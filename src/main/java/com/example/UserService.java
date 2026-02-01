package com.example;

import java.sql.*;

public class UserService {

    private final Connection connection;

    // inject connection (mockable)
    public UserService(Connection connection) {
        this.connection = connection;
    }

    public boolean findUser(String username) throws SQLException {
        String sql = "SELECT id FROM users WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public int deleteUser(String username) throws SQLException {
        String sql = "DELETE FROM users WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            return ps.executeUpdate();
        }
    }

    // remove dead method
}
