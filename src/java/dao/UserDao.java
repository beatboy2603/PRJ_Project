/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.File;
import models.User;

/**
 *
 * @author ADMIN
 */
public class UserDao {

    private Connection connection;

    public UserDao() throws Exception {
        connection = (new DBContext()).getConnection();
    }

    public boolean checkUser(String username, String pass) {
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select [username], [pass] from [users] "
                            + "where username = ? and pass = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pass);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                return false;
            } else {
                if (!username.equals(rs.getString("username")) || !pass.equals(rs.getString("pass"))) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean checkUser(String username) {
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select [username] from [users] "
                            + "where username = ?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public User getUser(String username) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from [users] "
                            + "where username = ?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user.setUsername(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setQuota(rs.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<String> getShareableUsers(int fId, String filterName) {
        List<String> usernames = new ArrayList<String>();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select username from users where username in"
                            + "(select username from users \n"
                            + "except \n"
                            + "select username from permit where Id = ?) and username like ? ");
            preparedStatement.setInt(1, fId);
            preparedStatement.setString(2, filterName);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                usernames.add(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usernames;
    }

    public void addUser(String username, String pass) throws SQLException {
        PreparedStatement preparedStatement = connection.
                prepareStatement("insert into Users values (?, ?, ?)");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, pass);
        preparedStatement.setInt(3, 0);
        preparedStatement.execute();
    }

    public void deleteUser(String username) throws SQLException {
        PreparedStatement preparedStatement = connection.
                prepareStatement("delete from Permit where username=?");
        preparedStatement.setString(1, username);
        preparedStatement.execute();
        preparedStatement = connection.
                prepareStatement("delete from Files where fOwner=?");
        preparedStatement.setString(1, username);
        preparedStatement.execute();
        preparedStatement = connection.
                prepareStatement("delete from [Users] where username=?");
        preparedStatement.setString(1, username);
        preparedStatement.execute();
        java.io.File index = new java.io.File("D:\\Documents\\NetBeansProjects\\PRJ_Project\\web\\fileManager\\"+username);
        String[] entries = index.list();
        for (String s : entries) {
            java.io.File currentFile = new java.io.File(index.getPath(), s);
            currentFile.delete();
        }
        index.delete();
    }

    public List<User> getAllUsers() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from Users");
        ResultSet rs = ps.executeQuery();
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setUsername(rs.getString(1));
            user.setPassword(rs.getString(2));
            user.setQuota(rs.getInt(3));
            users.add(user);
        }
        return users;
    }

    public void updateQuota(String username, int quota) {
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("update [users] set quota = ? "
                            + "where username = ?");
            preparedStatement.setInt(1, quota);
            preparedStatement.setString(2, username);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
