/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author User
 */
public class DBConnection {

    Connection Con = null;
    Statement Stmt = null;

    
    public DBConnection(){
         
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/office_hours?useSSL=false";
            String user = "root";
            String password = "1234";
            Con = DriverManager.getConnection(url, user, password);
            Stmt = Con.createStatement();
               System.out.print("###################################data base is zy el fol");
               
        } catch (Exception ex) {
            
               System.out.print("################################### Error in data base");
               
            ex.printStackTrace();

        }
    }
    
    public Connection getConnection() {
        return Con;

    }

    public Statement getStatement() {
        return Stmt;
    }

    public boolean closeConnection( Connection Con, Statement Stmt) {
        try {
            Stmt.close();
            Con.close();
            return true;
        } catch (Exception ex) {
         
            ex.printStackTrace();
            return false;
        }

    }

}
