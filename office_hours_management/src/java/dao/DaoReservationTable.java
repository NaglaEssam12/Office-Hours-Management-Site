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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import model.OfficeHours;

/**
 *
 * @author User
 */
public class DaoReservationTable {
    
        DBConnection dBConnection = new DBConnection();
        Connection Con = null;
        Statement Stmt = null;
    
        ArrayList<Integer> fromUserIDs = new ArrayList<Integer>();

    public ArrayList<Integer> getFromUserIDs() {
        return fromUserIDs;
    }
        
     public ArrayList<Integer> selectOfficeHoursIDs() {
         
         
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;
        ArrayList<Integer> officeHoursIDs = new ArrayList<Integer>();
        
        try {

            query = "select officeHoursID,fromUserID from reservation;";
            Con = dBConnection.getConnection();
            Stmt = dBConnection.getStatement();

            RS = Stmt.executeQuery(query);
            
            while(RS.next()){
              officeHoursIDs.add(RS.getInt("officeHoursID"));       
            }
            
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
        return officeHoursIDs;
    }
    
     
     public ArrayList<OfficeHours> selectTodayOfficeHours(ArrayList<Integer> officeHoursIDs)
     {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;
        ArrayList<OfficeHours> officeHours = new ArrayList<OfficeHours>();
         try {
             String today ;
             today = LocalDate.now().toString();
             System.out.print(today);
             OfficeHours officeHour = new OfficeHours();
          for(int i = 0; i < officeHoursIDs.size(); ++i){
              
            query = "select date,fromTime,toTime,userID,officeHoursID,sent from office_hours where officeHoursID = '"+officeHoursIDs.get(i)+"';";
            Con = dBConnection.getConnection();
            Stmt = dBConnection.getStatement();
            RS = Stmt.executeQuery(query);
            
            while(RS.next()){
                
             if((RS.getDate("date").toString()).equals(today) && RS.getString("sent").equals("false"))
             {
                  officeHour = new OfficeHours();
                  officeHour.setDate(RS.getDate("date").toLocalDate());
                  officeHour.setFromTime(RS.getString("fromTime").toString());
                  officeHour.setToTime(RS.getString("toTime").toString());
                  officeHour.setUserID(RS.getInt("userID"));
                  officeHour.setOfficeHoursID(RS.getInt("officeHoursID"));
                  officeHours.add(officeHour); 
                  // this function fills the "fromUser" array that we will sent them notifications.
                  setFromUserIdBasedOnTodayOfficeHour(RS.getInt("officeHoursID"));
                  // This function invert the status of officeHours to "true" to make the system know that this notification sent
                  // and doesn't send it again.
                  invertSentStatus(RS.getInt("officeHoursID") );
                  System.out.print("###################DoNNNNNNNNNNNNNNNNNNNNNNNNNNNNEEE2");
                  
             }           
            }
          }
        } catch (Exception e) {
             
            System.out.print("###################Error in data base:" + e);

        }
         return officeHours;
     }
    
       public String getUserEmailByID(Integer ID) {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;
       String email = "";
        try {

            query = "select email from user where userID = '" + ID + "';";
            Connection Con = dBConnection.getConnection();
            Statement Stmt = dBConnection.getStatement();

            RS = Stmt.executeQuery(query);
            if (RS.next()) {
                email = RS.getString("email");
            }
            Stmt.close();
            Con.close();
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
        return email;
    }


       public void setFromUserIdBasedOnTodayOfficeHour(Integer officeHoursID)
       {
        dBConnection = new DBConnection();
        String query = "";
        ResultSet RS = null;
     
         try {
 
             query = "select fromUserID from reservation where officeHoursID = '"+officeHoursID+"';";
             Con = dBConnection.getConnection();
             Stmt = dBConnection.getStatement();
             RS = Stmt.executeQuery(query);
            
            while(RS.next()){
                
                 fromUserIDs.add(RS.getInt("fromUserID"));
            
            }
          
            
        } catch (Exception e) {
            System.out.print("###################Error in data base:" + e);

        }
         
       }

       public void invertSentStatus(Integer officeHoursID ){
       
           
        dBConnection = new DBConnection();
        String query = "";
        int RS ;
        
         try {
             

            query = "UPDATE office_hours.office_hours SET "
                   + "sent = '" + "true" + "' "
                   +"WHERE officeHoursID = '" + officeHoursID + "';";
        
            
            Con = dBConnection.getConnection();
            Stmt = dBConnection.getStatement();
            RS = Stmt.executeUpdate(query);
            
       
        } catch (Exception e) {
          
            System.out.print("###################Error in data base:" + e);

        }

       }


}