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
import java.util.logging.Level;
import java.util.logging.Logger;
import models.File;

/**
 *
 * @author ADMIN
 */
public class FileDao {

    private Connection connection;

    public FileDao() throws Exception {
        connection = (new DBContext()).getConnection();
    }

    public List<File> getAllFiles(String fOwner) {
        List<File> files = new ArrayList<File>();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from Files where fOwner = ?");
            preparedStatement.setString(1, fOwner);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                File file = new File();
                file.setfId(rs.getInt("fId"));
                file.setfName(rs.getString("fName"));
                file.setfSize(rs.getString("fSize"));
                file.setfOwner(rs.getString("fOwner"));
                file.setPrivacy(rs.getString("Privacy"));
                files.add(file);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return files;
    }

    public List<File> getAllSharedFiles(String username) {
        List<File> files = new ArrayList<File>();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from Files where fId in "
                            + "(select id from Permit where username = ?\n"
                            + "except\n"
                            + "select fId from Files where fOwner = ?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                File file = new File();
                file.setfId(rs.getInt("fId"));
                file.setfName(rs.getString("fName"));
                file.setfSize(rs.getString("fSize"));
                file.setfOwner(rs.getString("fOwner"));
                file.setPrivacy(rs.getString("Privacy"));
                files.add(file);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return files;
    }

    public boolean checkFile(String fOwner, String fName) {
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from Files where fOwner = ? and fName = ?");
            preparedStatement.setString(1, fOwner);
            preparedStatement.setString(2, fName);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public File getFile(String fOwner, String fName) {
        File file = new File();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from Files where fOwner = ? and fName = ?");
            preparedStatement.setString(1, fOwner);
            preparedStatement.setString(2, fName);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                file.setfId(rs.getInt("fId"));
                file.setfName(rs.getString("fName"));
                file.setfSize(rs.getString("fSize"));
                file.setfOwner(rs.getString("fOwner"));
                file.setPrivacy(rs.getString("Privacy"));
                return file;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public File getFile(int fId) {
        File file = new File();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from Files where fId = ?");
            preparedStatement.setInt(1, fId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                file.setfId(rs.getInt("fId"));
                file.setfName(rs.getString("fName"));
                file.setfSize(rs.getString("fSize"));
                file.setfOwner(rs.getString("fOwner"));
                file.setPrivacy(rs.getString("Privacy"));
                return file;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateFile(int fId, String fName, String fSize, String fOwner, String privacy) throws SQLException {
        PreparedStatement preparedStatement = connection.
                prepareStatement("update files set fName = ?, fSize = ?, fOwner = ?, privacy = ? where fId = ?");
        preparedStatement.setString(1, fName);
        preparedStatement.setString(2, fSize);
        preparedStatement.setString(3, fOwner);
        preparedStatement.setString(4, privacy);
        preparedStatement.setInt(5, fId);
        preparedStatement.execute();
    }

    public void addFile(String fName, String fSize, String fOwner, String privacy) throws SQLException {
        PreparedStatement preparedStatement = connection.
                prepareStatement("insert into files (fName, fSize, fOwner, Privacy) values (?, ?, ?, ?)");
        preparedStatement.setString(1, fName);
        preparedStatement.setString(2, fSize);
        preparedStatement.setString(3, fOwner);
        preparedStatement.setString(4, privacy);
        preparedStatement.execute();
    }

    public void deleteFile(int fId) throws SQLException {
        PreparedStatement preparedStatement = connection.
                prepareStatement("delete from files where fid = ?");
        preparedStatement.setInt(1, fId);
        preparedStatement.execute();
    }
}
