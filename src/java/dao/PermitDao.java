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
import models.Permit;

/**
 *
 * @author ADMIN
 */
public class PermitDao {

    private Connection connection;

    public PermitDao() throws Exception {
        connection = (new DBContext()).getConnection();
    }

    public boolean checkPermit(int fId, String username) {
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from Permit where Id = ? and username = ?");
            preparedStatement.setInt(1, fId);
            preparedStatement.setString(2, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean addPermit(int fId, String username) {
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("insert into permit values(?,?)");
            preparedStatement.setInt(1, fId);
            preparedStatement.setString(2, username);
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PermitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public void deleteAllPermit(int fId){
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("delete from permit where id = ?");
            preparedStatement.setInt(1, fId);
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PermitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteOnePermit(int fId, String username){
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("delete from permit where id = ? and username = ?");
            preparedStatement.setInt(1, fId);
            preparedStatement.setString(2, username);
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PermitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
