/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author User
 */
public class OfficeHours {
   int officeHoursID;
    String fromTime;
    String toTime;
    LocalDate date;
    String state;
    String location;
    int userID;
    String sent;

    public int getOfficeHoursID() {
        return officeHoursID;
    }

    public void setOfficeHoursID(int officeHoursID) {
        this.officeHoursID = officeHoursID;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

      
}
