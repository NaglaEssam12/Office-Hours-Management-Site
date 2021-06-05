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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.OfficeHours;

/**
 *
 * @author DELL
 */
public class DaoViewOfficeHours {
    DBConnection dBConnection;
    Connection Con;
    Statement Stmt;
    public ArrayList<OfficeHours> findScheduleByStaffID(Integer staffMemberID) {
        ArrayList<OfficeHours> officeHoursSchedule = new ArrayList<>();

        dBConnection = new DBConnection();
        Con = dBConnection.getConnection();
        Stmt = dBConnection.getStatement();
        String query = "";
        ResultSet RS = null;

        try {
            query = "SELECT officeHoursID, fromTime, toTime, date, state, location FROM office_hours.office_hours where userID = '" + staffMemberID + "' ; ";
            RS = Stmt.executeQuery(query);

            while(RS.next()) {
                OfficeHours officeHoursObject = new OfficeHours();
                officeHoursObject.setOfficeHoursID(RS.getInt("officeHoursID"));
                officeHoursObject.setFromTime(RS.getString("fromTime"));
                officeHoursObject.setToTime(RS.getString("toTime"));
                officeHoursObject.setDate(LocalDate.parse(RS.getString("date")));
                officeHoursObject.setState(RS.getString("state"));
                officeHoursObject.setLocation(RS.getString("location"));
                officeHoursSchedule.add(officeHoursObject);
            }

            dBConnection.closeConnection(Con, Stmt);

        } catch (Exception e) {
            System.out.print("###################Error in data base (in DaoViewOfficeHours class):" + e);
        }

        return officeHoursSchedule;
    }
}
