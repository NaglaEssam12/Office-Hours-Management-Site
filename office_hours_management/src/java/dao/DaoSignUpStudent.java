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
 * @author User
 */
public class DaoSignUpStudent {
   
    DBConnection dBConnection;
    public boolean signUp(User user){
        
               String query ="";
          dBConnection = new DBConnection();            
            int RS ;

                 try {
          
               query = "INSERT INTO office_hours.user (userName, displayName, email, type, password, department) VALUES ( "
                       + "'"+user.getUserName()+"',"
                       + " '"+user.getUserDisplayName()+"',"
                       + " '"+user.getUserEmail()+"',"
                       + " '"+user.getUserType()+"',"
                       + " '"+user.getUserPassword()+"',"
                       + " '"+user.getUserDepartment()+"' );";
  
                 Connection Con = dBConnection.getConnection();
                 Statement Stmt = dBConnection.getStatement();
            
             RS =Stmt.executeUpdate(query);
            
           
           
             dBConnection.closeConnection(Con, Stmt);
             return true;

        } catch (Exception e) {
         System.out.print("####### " + e);
          return false;
        }
       
    }
    
       
     public boolean isPasswordExisted(String password){
         
         return false;
    }
    
}
