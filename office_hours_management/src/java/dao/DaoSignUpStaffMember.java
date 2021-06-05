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
import java.util.ArrayList;
import model.User;

/**
 *
 * @author User
 */
public class DaoSignUpStaffMember {
    
    DBConnection  dBConnection;
    
    public boolean signUp( User user){
         String query ="";
          dBConnection = new DBConnection();            
            int RS ;

                 try {
          
               query = "INSERT INTO office_hours.user (userName, displayName, email, type, password, subjectName) VALUES ( "
                       + "'"+user.getUserName()+"',"
                       + " '"+user.getUserDisplayName()+"',"
                       + " '"+user.getUserEmail()+"',"
                       + " '"+user.getUserType()+"',"
                       + " '"+user.getUserPassword()+"',"
                       + " '"+user.getUserSubject()+"' );";
  
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
    public String getAllSubjectNames(String subjectName)
    {
        dBConnection = new DBConnection();  
        String query ="";
        String subjectID = "";
        ArrayList<String>subjectNames = new ArrayList<>();
        ResultSet RS = null;
        try {
            
            query = "select subjectID from subject where subjectName ='"+subjectName+"'";  
            Connection Con = dBConnection.getConnection();
            Statement Stmt = dBConnection.getStatement();

            
            RS =Stmt.executeQuery(query);
            if(RS.next())
            {
                subjectID = RS.getString("subjectID");
            }
            Con.close();
            Stmt.close();

        } catch (Exception e) {
         System.out.print("####### " + e);
        }
        return subjectID;
        
    }
    public void insertSubjectName(String subjectName)
    {
        String query ="";
        dBConnection = new DBConnection();            
            int RS ;

                 try {
          
               query = "INSERT INTO office_hours.subject (subjectName) VALUES ( "
                       + "'"+subjectName+"');";
  
                 Connection Con = dBConnection.getConnection();
                 Statement Stmt = dBConnection.getStatement();
            
             RS =Stmt.executeUpdate(query);
            
           
           
             Con.close();
            Stmt.close();

        } catch (Exception e) {
         System.out.print("####### " + e);
        }
        
    }
    public void insertStaffMemberSubject(String staffMemberID, String subjectID)
    {
        String query ="";
        dBConnection = new DBConnection();            
            int RS ;

                 try {
          
               query = "INSERT INTO office_hours.user_subject (staffMemberID,subjectID) VALUES ( "
                       + "'"+staffMemberID+"',"
                       + "'"+subjectID+"');";
  
                 Connection Con = dBConnection.getConnection();
                 Statement Stmt = dBConnection.getStatement();
            
             RS =Stmt.executeUpdate(query);
            
           
           
             Con.close();
            Stmt.close();

        } catch (Exception e) {
         System.out.print("####### " + e);
        }
        
    }
    
     public boolean isPasswordExisted(String password){
         
         return false;
    }
    
}
