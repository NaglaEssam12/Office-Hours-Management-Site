/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database_connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Reservation;
import model.Subject;
import model.User;

/**
 *
 * @author DELL
 */
public class DaoSelectAll {
    DBConnection dBConnection;
    Connection Con;
    Statement Stmt;
    ResultSet RS = null;
        
    public ArrayList<Subject> selectAllSubjects() {
        dBConnection = new DBConnection();
        Con = dBConnection.getConnection();
        Stmt = dBConnection.getStatement();
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        String query = "";

        try {
            query = "SELECT subjectID, subjectName FROM office_hours.subject;";
            RS = Stmt.executeQuery(query);

            while(RS.next()) {
                    Subject subject = new Subject();
                    subject.setSubjectID(RS.getInt("subjectID"));
                    subject.setSubjectName(RS.getString("subjectName"));
                    subjects.add(subject);
            }
        } catch (Exception e) {
            System.out.print("###################Error in data base (in DaoSelectAll class):" + e);
        }
        return subjects;
    }
    
    public ArrayList<User> selectAllStaffMembers() {
        dBConnection = new DBConnection();
        Con = dBConnection.getConnection();
        Stmt = dBConnection.getStatement();
        ArrayList<User> staffMembers = new ArrayList<User>();
        String query = "";

        try {
            query = "SELECT userID, displayName, type FROM office_hours.user WHERE type = 'Dr' OR type = 'TA';";
            RS = Stmt.executeQuery(query);

            while(RS.next()) {
                    User staffMember = new User();
                    staffMember.setUserID(RS.getInt("userID"));
                    staffMember.setUserDisplayName(RS.getString("displayName"));
                    staffMember.setUserType(RS.getString("type"));
                    staffMembers.add(staffMember);
            }
            
        } catch (Exception e) {
            System.out.print("###################Error in data base (in DaoSelectAll class):" + e);
        }
        return staffMembers;
    }
    
    public ArrayList<Reservation> selectAllStudentReservations(Integer studentID){
        dBConnection = new DBConnection();
        Con = dBConnection.getConnection();
        Stmt = dBConnection.getStatement();
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        String query = "";

        try {
            query = "SELECT reservationID, officeHoursID, toUserID FROM office_hours.reservation WHERE fromUserID = '"+studentID+"';";
            RS = Stmt.executeQuery(query);

            while(RS.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setReservationID(RS.getInt("reservationID"));
                    reservation.setOfficeHoursID(RS.getInt("officeHoursID"));
                    reservation.setToUserID(RS.getInt("toUserID"));
                    reservations.add(reservation);
            }
            
        } catch (Exception e) {
            System.out.print("###################Error in data base (in DaoSelectAll class):" + e);
        }
        return reservations;
    }
    
    public Integer getUserIDByEmail(String email){
        dBConnection = new DBConnection();
        Con = dBConnection.getConnection();
        Stmt = dBConnection.getStatement();
        Integer userID = 0;
        String query = "";

        try {
            query = "SELECT userID FROM office_hours.user WHERE email = '"+email+"';";
            RS = Stmt.executeQuery(query);

            while(RS.next()) {
                userID = RS.getInt("userID");
            }
            
        } catch (Exception e) {
            System.out.print("###################Error in data base (in DaoSelectAll class):" + e);
            return userID;
        }
        return userID;
    }
    
    public User selectAllUserData(String email){
        dBConnection = new DBConnection();
        Con = dBConnection.getConnection();
        Stmt = dBConnection.getStatement();
        User user = new User();
        String query = "";

        try {
            query = "SELECT userID, userName, displayName, type, password, subjectName, department FROM office_hours.user WHERE email = '"+email+"';";
            RS = Stmt.executeQuery(query);

            while(RS.next()) {
                user.setUserID(RS.getInt("userID"));
                user.setUserName(RS.getString("userName"));
                user.setUserDisplayName(RS.getString("displayName"));
                user.setUserType(RS.getString("type"));
                user.setUserPassword(RS.getString("password"));
                user.setUserSubject(RS.getString("subjectName"));
                user.setUserDepartment(RS.getString("department"));
            }
            
        } catch (Exception e) {
            System.out.print("###################Error in data base (in DaoSelectAll class):" + e);
        }
        
        return user;
    }
    
    public void closeConnection() throws SQLException{
        dBConnection.closeConnection(Con, Stmt);
        RS.close();
    }
}