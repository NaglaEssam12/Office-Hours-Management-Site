/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database_connection.DBConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OfficeHours;

/**
 *
 * @author ME
 */
public class DaoOfficeHoursTable {
    DBConnection dBConnection = new DBConnection();
    Connection Con = null;
    Statement Stmt = null;
    public void insertData(OfficeHours officeHours)
    {
        dBConnection = new DBConnection();
        Connection Con = null;
        Statement Stmt = null;
        String query = "";
        int RS;
         try {
          
               query = "INSERT INTO office_hours (fromTime, toTime, date, state, location, userID, sent) VALUES ( "
                       + "'"+ officeHours.getFromTime()+"',"
                       + " '"+officeHours.getToTime()+"',"
                       + " '"+officeHours.getDate()+"',"
                       + " '"+officeHours.getState()+"',"
                       + " '"+officeHours.getLocation()+"',"
                       + " '"+officeHours.getUserID()+"',"
                       + " '"+officeHours.getSent()+"' );";
  
                Con = dBConnection.getConnection();
                Stmt = dBConnection.getStatement();
            
             RS =Stmt.executeUpdate(query);
             Con.close();
             Stmt.close();

        } catch (Exception e) {
         System.out.print("####### " + e);
          
        }
        
    }
    public void updateDate(String officeHoursID , OfficeHours officeHours)
    {
        dBConnection = new DBConnection();
        Connection Con = null;
        Statement Stmt = null;
        String query = "";
        int RS;
        try {
          
               query = "UPDATE office_hours set fromTime = '"+ officeHours.getFromTime()+"',"
                       + " toTime = '"+officeHours.getToTime()+"',"
                       + " date = '"+officeHours.getDate()+"',"
                       + " state = '"+officeHours.getState()+"',"
                       + " location = '"+officeHours.getLocation()+"',"
                       + " userID = '"+officeHours.getUserID()+"'"
                       + " sent = '"+officeHours.getSent()+"'"
                       + "where officeHoursID = '"+officeHoursID+"';";
                      
  
                Con = dBConnection.getConnection();
                Stmt = dBConnection.getStatement();
            
             RS =Stmt.executeUpdate(query);
             Con.close();
             Stmt.close();

        } catch (Exception e) {
         System.out.print("####### " + e);
          
        }
    }
    public void deleteData(String officeHoursID)
    {
         dBConnection = new DBConnection();
        Connection Con = null;
        Statement Stmt = null;
        String query = "";
        int RS;
        try {
                query = "delete from office_hours where officeHoursID = '"+officeHoursID+"'";
                Con = dBConnection.getConnection();
                Stmt = dBConnection.getStatement();
            
             RS =Stmt.executeUpdate(query);
             Con.close();
             Stmt.close();

        } catch (Exception e) {
         System.out.print("####### " + e);
          
        }
        
    }
    public ResultSet selectAllData()
    {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;
        try {
                query = "select * from office_hours;";
                Con = dBConnection.getConnection();
                Stmt = dBConnection.getStatement();
            
             RS =Stmt.executeQuery(query);
             //Con.close();
             //Stmt.close();

        } catch (Exception e) {
         System.out.print("####### " + e);
          
        }
        return RS;
    }
    public String selectOfficeHours(String fromTime, String toTime, String date, String userID) {
        dBConnection = new DBConnection();
        Connection Con = null;
        Statement Stmt = null;
        String query = "";
        ResultSet RS = null;
        String ID = "";

        try {

            query = "select officeHoursID from office_hours where fromTime =  '" + fromTime + "'and toTime = '" + toTime + "'and date = '" + date + "' and userID = '" + userID + "';";
            Con = dBConnection.getConnection();
            Stmt = dBConnection.getStatement();

            RS = Stmt.executeQuery(query);
            while(RS.next())
            {
                ID = RS.getString("officeHoursID");
            }

            Stmt.close();
            Con.close();
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
        return ID;
    }
    public ArrayList<String> selectSlotOfficeHours(String fromTime, String toTime, String userID) {
        dBConnection = new DBConnection();
        Connection Con = null;
        Statement Stmt = null;
        String query = "";
        ResultSet RS = null;
        String ID = "" , date = "";
        ArrayList<String>IDs = new ArrayList<>();
        ArrayList<String>dates = new ArrayList<>();
        try {

            query = "select officeHoursID from office_hours where fromTime =  '" + fromTime + "'and toTime = '" + toTime + "'and userID = '" + userID + "';";
            Con = dBConnection.getConnection();
            Stmt = dBConnection.getStatement();

            RS = Stmt.executeQuery(query);
            while(RS.next())
            {
                ID = RS.getString("officeHoursID");
                IDs.add(ID);
                //date = RS.getString("date");
                //dates.add(date);
            }

            Stmt.close();
            Con.close();
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
        return IDs;
    }
    public ArrayList<String> selectOfficeHoursIDsByDate(String fromDate, String toDate, String userID) {
        dBConnection = new DBConnection();
        Connection Con = null;
        Statement Stmt = null;
        String query = "";
        ResultSet RS = null;
        String ID = "" , date = "";
        ArrayList<String>IDs = new ArrayList<>();
        ArrayList<String>dates = new ArrayList<>();
        try {
            System.out.println("ssss");
            query = "select officeHoursID from office_hours where date between '" + fromDate + "'and '" + toDate + "' and userID = '" + userID + "';";
            Con = dBConnection.getConnection();
            Stmt = dBConnection.getStatement();

            RS = Stmt.executeQuery(query);
            while(RS.next())
            {
                ID = RS.getString("officeHoursID");
                System.out.println("ID " + ID);
                IDs.add(ID);
            }

            Stmt.close();
            Con.close();
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
        return IDs;
    }
    public String getSpecificSlotDate(String officeHoursID)
    {
        dBConnection = new DBConnection();
        Connection Con = null;
        Statement Stmt = null;
        String query = "";
        ResultSet RS ;
        String date = "";
        int rows;
        try {
            Con = dBConnection.getConnection();
            Stmt = dBConnection.getStatement();
            query = "select date from office_hours where officeHoursID =  '" + officeHoursID + "';";
            RS = Stmt.executeQuery(query);
            while(RS.next())
            {
                date = RS.getString("date");
            }
            
            Con.close();
            Stmt.close();
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
        return date;
    }
    public ResultSet getSpecificOfficeHoursIDS(String date)
    {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;
        String fromUserID = "";

        try {

            query = "select officeHoursID from office_hours where date =  '" + date + "';";
            Con = dBConnection.getConnection();
            Stmt = dBConnection.getStatement();

            RS = Stmt.executeQuery(query);
            
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
        return RS;
    }
    public ResultSet getSpecificSlots(String staffMemberID)
    {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;
        String fromUserID = "";

        try {

            query = "select fromTime,toTime,date from office_hours where userID =  '" + staffMemberID + "';";
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
