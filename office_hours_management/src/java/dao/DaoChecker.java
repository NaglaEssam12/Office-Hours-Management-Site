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
 * @author User
 */
public class DaoChecker {
    
    DBConnection dBConnection; 
    public boolean isDiplayNameExisted(String displayName){
        
       
           dBConnection = new DBConnection(); 
         
            String query ="";
            ResultSet RS = null;
            boolean status = false;
       
                 try {
          
          query = "select displayName from office_hours.user where displayName = '"+displayName+"' ; ";
  
            Connection Con = dBConnection.getConnection();
            Statement Stmt = dBConnection.getStatement();

            
            RS =Stmt.executeQuery(query);

              if(RS.next())
              {
                    
                  status = true;
              }
             
      
             Stmt.close();
             Con.close();
          
        } catch (Exception e) {
           System.out.print("###################Error in data base:" + e);
          
        }
                 return status;

    }
    public boolean isEmailExisted(String email){
    
    
           
           dBConnection = new DBConnection(); 
         
            String query ="";
            ResultSet RS = null;
            boolean status = false;
       
                 try {
          
          query = "select email from office_hours.user where email = '"+email+"' ; ";
  
            Connection Con = dBConnection.getConnection();
            Statement Stmt = dBConnection.getStatement();

            
            RS =Stmt.executeQuery(query);
        
               
              if(RS.next())
              {
                    
                  status = true;
              }
             
      
             Stmt.close();
             Con.close();
          
        } catch (Exception e) {
           System.out.print("###################Error in data base:" + e);
          
        }
                 return status;
    
    
    }
    
}
