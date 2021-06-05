/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database_connection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Message;

/**
 *
 * @author ME
 */
public class DaoViewStaffMemberMessages {

    DBConnection dBConnection = new DBConnection();
    Connection Con = null;
    Statement Stmt = null;
    public String getSpecificSubjectName(String userID)
    {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;
        ArrayList<String>IDs = new ArrayList<>();
        String subjectName = "";

        try {
            System.out.println("Tmmmmmmmmmamm");
            query = "select subjectName from user where userID = '" + userID + "';";
            Connection Con = dBConnection.getConnection();
            Statement Stmt = dBConnection.getStatement();

            RS = Stmt.executeQuery(query);
            if(RS != null){
            while (RS.next()) {
               
                subjectName = RS.getString("subjectName");
              
            }
            }
            Stmt.close();
            Con.close();
            
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
        return subjectName;
        
    }
    public ArrayList<String> getSpecificSubjectToIDs(String subjectName , String fromID)
    {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;
        ArrayList<String>IDs = new ArrayList<>();
        String ID = "";

        try {
            System.out.println("Tmmmmmmmmmamm");
            query = "select userID from user where subjectName = '" + subjectName + "'and userID <> '"+fromID+"';";
            Connection Con = dBConnection.getConnection();
            Statement Stmt = dBConnection.getStatement();

            RS = Stmt.executeQuery(query);
            if(RS != null){
            while (RS.next()) {
               
                ID = RS.getString("userID");
                IDs.add(ID);
            }
            }
            Stmt.close();
            Con.close();
            
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
         System.out.print("###################IDS: " + IDs);
        return IDs;
        
    }
    public String getSpecificToID(String displayName)
    {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;
        String ID = "";

        try {

            query = "select userID from user where displayName = '" + displayName + "';";
            Connection Con = dBConnection.getConnection();
            Statement Stmt = dBConnection.getStatement();

            RS = Stmt.executeQuery(query);
            while (RS.next()) {
               
                ID = RS.getString("userID");
                System.out.println("ID " + ID);
            }
            Stmt.close();
            Con.close();
            
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
        return ID;
    }
    public String getSendToID(String messageID) {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;
        String ID = "";

        try {

            query = "select fromUserID from message where messageID = '" + messageID + "';";
            Connection Con = dBConnection.getConnection();
            Statement Stmt = dBConnection.getStatement();

            RS = Stmt.executeQuery(query);
            while (RS.next()) {
               
                ID = RS.getString("fromUserID");
                System.out.println("ID " + ID);
            }
            Stmt.close();
            Con.close();
            
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
        return ID;

    }

    public String getStaffMemberID(String email) {
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

    public void insertMessage(Message message) {
        String query = "";
        dBConnection = new DBConnection();
        int RS;
        boolean flag = false;

        try {

            query = "INSERT INTO message (fromUserID,toUserID, actualMassage,date) VALUES ( "
                    + "'" + message.getFromUserID() + "',"
                    + " '" + message.getToUserID() + "',"
                    + " '" + message.getActualMassage() + "',"
                    + " '" + message.getDate() + "')";

            Connection Con = dBConnection.getConnection();
            Statement Stmt = dBConnection.getStatement();

            RS = Stmt.executeUpdate(query);
            flag = true;
            Stmt.close();
            Con.close();
        } catch (Exception e) {
            System.out.print("####### " + e);
           
        }
        
    }

    public ResultSet selectMessages(String ID) {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;

        try {

            query = "select messageID,fromUserID,actualMassage,date from message where toUserID =  '" + ID + "';";
            Con = dBConnection.getConnection();
            Stmt = dBConnection.getStatement();

            RS = Stmt.executeQuery(query);

            //Stmt.close();
            // Con.close();
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
        return RS;
    }
     public ResultSet selectColumns() {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;

        try {

            query = "select displayName,subjectName,type from user;";
            Con = dBConnection.getConnection();
            Stmt = dBConnection.getStatement();

            RS = Stmt.executeQuery(query);

            
            //Stmt.close();
            // Con.close();
        } catch (Exception e) {
            System.out.print("###################*** Error in data base:" + e);

        }
        return RS;
    }

     
    public ResultSet selectStaffColumns() {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;

        try {

            query = "select displayName,subjectName,type from user where type <> 'student';";
            Con = dBConnection.getConnection();
            Stmt = dBConnection.getStatement();

            RS = Stmt.executeQuery(query);

            
            //Stmt.close();
            // Con.close();
        } catch (Exception e) {
            System.out.print("###################*** Error in data base:" + e);

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
