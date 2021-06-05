/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database_connection.DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.User;

public class DaoSignInUser {
  
    
 DBConnection  dBConnection;
 public boolean signIn(User user){
         dBConnection = new DBConnection(); 
         
             String query ="";
            ResultSet RS = null;
            boolean status = false;
         
                 try {
          
          query = "select * from user where email =  '" + user.getUserEmail() + "' and password = '"+user.getUserPassword()+"'; ";
  
            Connection Con = dBConnection.getConnection();
            Statement Stmt = dBConnection.getStatement();

            
            RS =Stmt.executeQuery(query);
            
              System.out.print("################### sign in done sucessfully");
           
              status = RS.next();
             Stmt.close();
           Con.close();

           return status;
        } catch (Exception e) {
           System.out.print("###################Error in data base:" + e);
          return status; 
        }
 
 }


 public boolean isUserStudent(User user){
   
          dBConnection = new DBConnection(); 
         
             String query ="";
            ResultSet RS = null;
            boolean status = false;
       
                 try {
          
          query = "select * from office_hours.user where email =  '" + user.getUserEmail() + "' and password = '"+user.getUserPassword()+"'; ";
  
            Connection Con = dBConnection.getConnection();
            Statement Stmt = dBConnection.getStatement();

            
            RS =Stmt.executeQuery(query);
             String type = "";
               
              if(RS.next())
              {
                    
                    type = RS.getString("type");
              }
             
      
             Stmt.close();
             Con.close();
          
              if(type.equals("student")){
             
                  return true;
              }else{
                 
                  return false;
              }


          
        } catch (Exception e) {
           System.out.print("###################Error in data base:" + e);
          return false; 
        }
 
 }
}
