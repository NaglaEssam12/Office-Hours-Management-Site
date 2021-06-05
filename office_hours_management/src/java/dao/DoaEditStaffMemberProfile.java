/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database_connection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import model.User;

/**
 *
 * @author ME
 */
public class DoaEditStaffMemberProfile {

    DBConnection dBConnection;
    Connection Con = null;
    Statement Stmt = null;

    public String defineID(String email) {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;
        String ID = "";

        try {

            query = "select userID from user where email =  '" + email + "';";
            Connection Con = dBConnection.getConnection();
            Statement Stmt = dBConnection.getStatement();

            RS = Stmt.executeQuery(query);
            while (RS.next()) {
                ID = RS.getString("userID");
            }
            Stmt.close();
            Con.close();
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
        return ID;
    }

    public void editProfile(User user, String ID) {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;

        try {

            Connection Con = dBConnection.getConnection();
            Statement Stmt = dBConnection.getStatement();
            query = "update user set displayName = '" +user.getUserDisplayName()+ "' ,"
                    + "email = '" +user.getUserEmail()+ "',"
                    + "type = '" +user.getUserType()+ "',"
                    + "password ='" +user.getUserPassword()+ "' ,"
                    + "subjectName = '" +user.getUserSubject()+ "'where userID = '"+ID+"';";
            int rows = Stmt.executeUpdate(query);
            Stmt.close();
            Con.close();
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
    }
    public ResultSet selectAllData(String email)
    {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;
        String ID = "";

        try {

            query = "select * from user where email =  '" + email + "';";
             Con = dBConnection.getConnection();
             Stmt = dBConnection.getStatement();

            RS = Stmt.executeQuery(query);
                       
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
        return RS;
        
    }
    public void closeConnection() throws SQLException
    {
         Stmt.close();
         Con.close();
    }

}
