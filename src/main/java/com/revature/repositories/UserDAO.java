package com.revature.repositories;

import com.revature.DBConnection.ConnectionManager;
import com.revature.models.Role;
import com.revature.models.User;

import javax.swing.text.html.Option;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;

public class UserDAO {

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    public Optional<User> getByUsername(String username) {
        try {
            String sql = "SELECT * FROM users where username = ?";
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRole(Role.valueOf(rs.getString("role")));
                user.setPassword(rs.getString("password"));

                return Optional.of(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<User> getByUserID(int id) {
        try {
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(Role.valueOf(rs.getString("role")));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<User> getByUserEmail(String email) {
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(Role.valueOf(rs.getString("role")));

                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     */
    public User create(User userToBeRegistered) {
        String sql = "INSERT into " +
                "users (first_name, last_name, username, password, email, role) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setString(1, userToBeRegistered.getFirst_name());
            pstmt.setString(2, userToBeRegistered.getLast_name());
            pstmt.setString(3, userToBeRegistered.getUsername());
            pstmt.setString(4, userToBeRegistered.getPassword());
            pstmt.setString(5, userToBeRegistered.getEmail());
            if(userToBeRegistered.getRole().equals(Role.EMPLOYEE)) {
                pstmt.setString(6, "EMPLOYEE");
            }
            else {
                pstmt.setString(6, "FINANCE_MANAGER");
            }

            pstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        Optional<User> user = getByUsername(userToBeRegistered.getUsername());

        return user.get();
    }

    public String getFullName(int id) {
        String full_name = "";

        try {
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                full_name = rs.getString("first_name") + " " + rs.getString("last_name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return full_name;
    }
}
