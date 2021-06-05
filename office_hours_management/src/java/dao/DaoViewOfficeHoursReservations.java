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

/**
 *
 * @author ME
 */
public class DaoViewOfficeHoursReservations {
        DBConnection dBConnection = new DBConnection();
        Connection Con = null;
        Statement Stmt = null;
    
        
    public ResultSet getReservationFromID(String officeHoursID) {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;
        String fromUserID = "";

        try {

            query = "select fromUserID from reservation where officeHoursID =  '" + officeHoursID + "';";
            Con = dBConnection.getConnection();
            Stmt = dBConnection.getStatement();

            RS = Stmt.executeQuery(query);
            
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
        return RS;
    }
    
     public ResultSet selectAllOfficeHoursIDs() {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;

        try {

            query = "select officeHoursID,fromUserID from reservation;";
            Con = dBConnection.getConnection();
            Stmt = dBConnection.getStatement();

            RS = Stmt.executeQuery(query);
            
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
        return RS;
    }
      
    public void selectReservarionSpecificSlotID(String officeHoursID)
    {
        dBConnection = new DBConnection();
        Connection Con = null;
        Statement Stmt = null;
        String query = "";
        String query1 = "";
        ResultSet RS ;
        String ID = "";
        //ArrayList<String>IDs = new ArrayList<>();
        int rows;
        try {
            Con = dBConnection.getConnection();
            Stmt = dBConnection.getStatement();
            query1 = "select reservationID from reservation where officeHoursID =  '" + officeHoursID + "';";
            RS = Stmt.executeQuery(query1);
            while(RS.next())
            {
                ID = RS.getString("reservationID");
                //IDs.add(ID);
                System.out.println("ID" + ID);
                cancelReservarionSpecificSlot(ID);
            }
            
            Con.close();
            Stmt.close();
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
       
    }
    
    public void cancelReservarionSpecificSlot(String reservationID)
    {
        dBConnection = new DBConnection();
        Connection Con = null;
        Statement Stmt = null;
        String query = "";
        int rows;
        try {
            Con = dBConnection.getConnection();
            Stmt = dBConnection.getStatement();
           
            
                query = "delete from reservation where reservationID =  '" + reservationID + "';";
                rows = Stmt.executeUpdate(query);
            
            
            Con.close();
            Stmt.close();
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
        
        
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
