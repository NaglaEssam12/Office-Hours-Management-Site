/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import database_connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.User;

/**
 *
 * @author DELL
 */
public class DaoFinder {

    DBConnection dBConnection;
    Connection Con;
    Statement Stmt;
    ResultSet RS = null;
    
    
    public ArrayList<User> findStaffBySubject(Integer subjectID) {
        dBConnection = new DBConnection();
        Con = dBConnection.getConnection();
        Stmt = dBConnection.getStatement();
        ArrayList<User> staffMembers = new ArrayList<>();
        ArrayList<Integer> staffMembersIDs = new ArrayList<>();
        String query = "";
        
        try {
            query = "SELECT staffMemberID FROM office_hours.user_subject where subjectID = '" + subjectID + "' ; ";
            RS = Stmt.executeQuery(query);

            while(RS.next()) {
                Integer staffMemberID = RS.getInt("staffMemberID");
                staffMembersIDs.add(staffMemberID);
            }
            
            for (int i = 0; i < staffMembersIDs.size(); i++) {
                User staffMember = new User();
                staffMember = findStaffByID(staffMembersIDs.get(i));
                staffMembers.add(staffMember);
            }
            
        } catch (Exception e) {
            System.out.print("###################Error in data base (in DaoFinder class):" + e);
        }

        return staffMembers;
    }
    
    public User findStaffByID(Integer staffMemberID) throws SQLException{
        dBConnection = new DBConnection();
        Con = dBConnection.getConnection();
        Stmt = dBConnection.getStatement();
        String query = "";
        User staffMember = new User();
        ResultSet RS1 = null;
        query = "SELECT displayName, type FROM office_hours.user where userID = '" + staffMemberID + "' ; ";
        RS1 = Stmt.executeQuery(query);

        if (RS1.next()) {
            staffMember.setUserID(staffMemberID);
            staffMember.setUserDisplayName(RS1.getString("displayName"));
            staffMember.setUserType(RS1.getString("type"));
            
        }
        
        RS1.close();
        return staffMember;
    }
    
    public String findContactForUser(Integer userID){
        dBConnection = new DBConnection();
        Con = dBConnection.getConnection();
        Stmt = dBConnection.getStatement();
        String query = "";
        String email = "";

        try {
            query = "SELECT email FROM office_hours.user where userID = '" + userID + "' ; ";
            RS = Stmt.executeQuery(query);

            if(RS.next()) {
                email = RS.getString("email");
            }

            //dBConnection.closeConnection(Con, Stmt);

        } catch (Exception e) {
            System.out.print("###################Error in data base (in DaoFinder class):" + e);
        }

        return email;
    }
    
    public void closeConnection() throws SQLException{
        dBConnection.closeConnection(Con, Stmt);
        RS.close();
    }
}
