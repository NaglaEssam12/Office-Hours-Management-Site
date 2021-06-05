/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database_connection.DBConnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ME
 */
public class DaoViewStudentDetails {
    DBConnection dBConnection = new DBConnection();
    Connection Con = null;
    Statement Stmt = null;
    public ResultSet selectStudentDetails(String displayName) {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;

        try {

            query = "select userID, userName, email, department from user where displayName =  '" + displayName + "';";
            Con = dBConnection.getConnection();
            Stmt = dBConnection.getStatement();

            RS = Stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
        return RS;
    }
    public ResultSet selectStudentDetailsByID(String userID) {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;

        try {

            query = "select userName,displayName,email from user where userID =  '" + userID + "';";
            Con = dBConnection.getConnection();
            Stmt = dBConnection.getStatement();

            RS = Stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
        return RS;
    }

    public void closeConnection() {
        try {
            Stmt.close();
            Con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoViewStaffMemberMessages.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
