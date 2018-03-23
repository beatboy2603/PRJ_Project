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
                //sign up case sensitive
//            }else{
//                if (!username.equals(rs.getString("username"))) {
//                    return false;
//                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<String> getShareableUsers(int fId) {
        List<String> usernames = new ArrayList<String>();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select username from users \n"
                            + "except \n"
                            + "select username from permit where Id = ? ");
            preparedStatement.setInt(1, fId);
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
                prepareStatement("insert into Users values (?, ?)");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, pass);
        preparedStatement.execute();
    }
}
