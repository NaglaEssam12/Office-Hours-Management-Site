/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database_connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author DELL
 */
public class DaoReserveAppointment {
    DBConnection  dBConnection;
    
    public boolean reserveAppointment(Integer studentID, Integer staffMemberID, Integer slotID){
        dBConnection = new DBConnection();
        Connection Con = dBConnection.getConnection();
        Statement Stmt = dBConnection.getStatement();
        String query = "";

        try {
            query = "INSERT INTO office_hours.reservation (officeHoursID, fromUserID, toUserID) VALUES ("
                       + " '"+slotID+"',"
                       + " '"+studentID+"',"
                       + " '"+staffMemberID+"');";
            
            int row = Stmt.executeUpdate(query);

            dBConnection.closeConnection(Con, Stmt);
            return true;
        } catch (Exception e) {
            System.out.print("###################Error in data base (in DaoReserveAppointment class):" + e);
            return false;
        }
    }
}
