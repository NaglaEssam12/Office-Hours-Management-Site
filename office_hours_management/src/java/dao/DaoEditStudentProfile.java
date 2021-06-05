/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database_connection.DBConnection;
import java.sql.Connection;
import java.sql.Statement;
import model.User;

/**
 *
 * @author DELL
 */
public class DaoEditStudentProfile {
    DBConnection dBConnection;
    Connection Con;
    Statement Stmt;
    
    public void editStudentProfile(User user){
        dBConnection = new DBConnection();
        Con = dBConnection.getConnection();
        Stmt = dBConnection.getStatement();
        String query = "";

        try {
            query = "UPDATE office_hours.user SET "
                   +"displayName = '"+user.getUserDisplayName()+"', "
                   +"email = '"+user.getUserEmail()+"', "
                   +"password = '"+user.getUserPassword()+"', "
                   +"department = '"+user.getUserDepartment()+"' "
                   +"WHERE userID = '"+user.getUserID()+"'";
            
            int row = Stmt.executeUpdate(query);

            dBConnection.closeConnection(Con, Stmt);
        } catch (Exception e) {
            System.out.print("###################Error in data base (in DaoEditStudentProfile class):" + e);
        }
    }
}
