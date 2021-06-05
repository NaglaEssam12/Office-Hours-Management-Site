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
import model.Notification;

/**
 *
 * @author User
 */
public class DaoNotifications {
    DBConnection dBConnection;
    public boolean sendNotificationToSystem(Notification notification){
    
                 String query ="";
          dBConnection = new DBConnection();            
            int RS ;

                 try {
          
               query = "INSERT INTO office_hours.notification (fromEmail,toEmail, notificationMessage) VALUES ( "
                       + "'"+notification.getFromEmail()+"',"
                       + "'"+notification.getToEmail()+"',"
                       + " '"+notification.getNotificationMessage()
                       + "' );";
  
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
  
    
    
    public String showMyNotifications(String email){
    
           String query ="";
           String output = "";
          dBConnection = new DBConnection();            
            ResultSet RS ;

                 try {
                     
                        System.out.print(email);
           
          
               query  = "SELECT * FROM  office_hours.notification WHERE toEmail ='" +  email + "' ;" ;
                 
  
                 Connection Con = dBConnection.getConnection();
                 Statement Stmt = dBConnection.getStatement();
                 
            
            RS = Stmt.executeQuery(query);
            
            
            while(RS.next()){
            
              
                output += "From Email: "+ RS.getString("fromEmail")
                       + "\nThe Message is:"
                       + RS.getString("notificationMessage")+"##" ;
                
            }
           
           
             dBConnection.closeConnection(Con, Stmt);
             return output;

        } catch (Exception e) {
         System.out.print("####### " + e);
          return output;
        }
      
    }
}
