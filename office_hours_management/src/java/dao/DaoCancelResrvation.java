/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database_connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class DaoCancelResrvation {
    DBConnection  dBConnection;
    Connection Con;
    Statement Stmt;
    
    public boolean cancelReservation(Integer reservationID){
        dBConnection = new DBConnection();
        Con = dBConnection.getConnection();
        Stmt = dBConnection.getStatement();
        String query = "";

        try {
            query = "DELETE FROM office_hours.reservation WHERE reservationID = '" + reservationID + "'; ";
            
            int row = Stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.print("###################Error in data base (in DaoCancelResrvation class):" + e);
            return false;
        }
    }
    
    public ArrayList<Integer> reservationFromIDToID(Integer reservationID){
        dBConnection = new DBConnection();
        Con = dBConnection.getConnection();
        Stmt = dBConnection.getStatement();
        ArrayList<Integer> fromIDToID = new ArrayList<>();
        String query = "";
        ResultSet RS = null;
        try {
            System.out.println("ID " + reservationID);
            query = "SELECT fromUserID, toUserID FROM office_hours.reservation WHERE reservationID = '" + reservationID + "'; ";
            RS = Stmt.executeQuery(query);
            
            while(RS.next()){
                fromIDToID.add(RS.getInt("fromUserID"));
                fromIDToID.add(RS.getInt("toUserID"));
            }
            
        } catch (Exception e) {
            System.out.print("###################Error in data base (in DaoCancelResrvation class):" + e);
        }
        
        return fromIDToID;
    }
    
    public void closeConnection() throws SQLException{
        dBConnection.closeConnection(Con, Stmt);
    }
}
