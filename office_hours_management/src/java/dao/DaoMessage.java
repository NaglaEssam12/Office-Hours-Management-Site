/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database_connection.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import model.Message;

/**
 *
 * @author DELL
 */
public class DaoMessage {
    DBConnection dBConnection;
    Connection Con;
    Statement Stmt;
    public boolean insertMessage(Message message){
        dBConnection = new DBConnection();
        Con = dBConnection.getConnection();
        Stmt = dBConnection.getStatement();
        String query = "";

        try {
            query = "INSERT INTO office_hours.message (fromUserID, toUserID, actualMassage, date) VALUES ("
                       + " '"+message.getFromUserID()+"',"
                       + " '"+message.getToUserID()+"',"
                       + " '"+message.getActualMassage()+"',"
                       + " '"+message.getDate()+"');";
            
            int row = Stmt.executeUpdate(query);

            //dBConnection.closeConnection(Con, Stmt);
            return true;
        } catch (Exception e) {
            System.out.print("###################Error in data base (in DaoMessage class):" + e);
            return false;
        }
    }
     public void closeConnection() throws SQLException{
        dBConnection.closeConnection(Con, Stmt);
       
    }
}
