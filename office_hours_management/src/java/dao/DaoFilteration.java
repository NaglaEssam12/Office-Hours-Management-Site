/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ME
 */
public class DaoFilteration {
    public ArrayList<String>filterTimes(ResultSet RS1) throws SQLException
    {
        ArrayList<String>Times = new ArrayList<>();
        while(RS1.next())
        {
            if(!Times.contains( RS1.getString("fromTime")+"-"+RS1.getString("toTime")))
            {
               Times.add((String)(RS1.getString("fromTime")+"-"+RS1.getString("toTime")));
            }
        }
        
        return Times;
    }
     public ArrayList<String>filterDates(ResultSet RS1) throws SQLException
    {
        ArrayList<String>dates = new ArrayList<>();
        while(RS1.next())
        {
            if(!dates.contains( RS1.getString("date")))
            {
               dates.add((String)(RS1.getString("date")));
            }
        }
        
        return dates;
    }
    
}
