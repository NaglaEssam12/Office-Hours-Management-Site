<%-- 
    Document   : view_reservations
    Created on : Jan 2, 2021, 5:57:12 PM
    Author     : ME
--%>

<%@page import="dao.DaoViewStudentDetails"%>
<%@page import="dao.DaoOfficeHoursTable"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="dao.DaoViewOfficeHoursReservations"%>
<%@page import="dao.DaoViewStaffMemberMessages"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet"  type="text/css" href="style.css" >
        <title>View Reservations</title>
    </head>
    <body>
        
        <%
              DaoViewStaffMemberMessages daoViewStaffMemberMessages = new DaoViewStaffMemberMessages();
              DaoOfficeHoursTable daoOfficeHoursTable = new DaoOfficeHoursTable();
              String email = request.getSession().getAttribute("email").toString();
              String staffMemberID = daoViewStaffMemberMessages.getStaffMemberID(email);
              String time = request.getParameter("time");
              String date = request.getParameter("date");
              String[] arrOfTime = time.split("-"); 
              String fromTime = arrOfTime[0];  String toTime = arrOfTime[1];
              DaoViewOfficeHoursReservations daoViewOfficeHoursReservations = new DaoViewOfficeHoursReservations();
              DaoViewStudentDetails daoViewStudentDetails = new DaoViewStudentDetails();
              System.out.println("fromTime " + fromTime + "ToTime" + toTime);
              boolean flag = false;
              ResultSet RS1 = daoOfficeHoursTable.selectAllData();
              try {
                while(RS1.next())
                {
                    if(RS1.getString("fromTime").equals(fromTime) && RS1.getString("toTime").equals(toTime)&& RS1.getString("date").equals(date))
                    {
                        
                        flag = true;
                        String officeHoursID = daoOfficeHoursTable.selectOfficeHours(fromTime, toTime, date, staffMemberID);
                        ResultSet RS = daoViewOfficeHoursReservations.getReservationFromID(officeHoursID);
                       
                        ResultSet RS2 =null; 
        %>
                          <h1 style="text-align:center;color: red;"> You have Reservations from </h1>
                          <table border = "1px" style="text-align: center; font-size: 25px;position:fixed;top:20%;left: 50%;transform: translate(-50%, -50%);">
                            <thead style="color:red">
                                <tr>
                                    <th>Student ID</th>
                                    <th>User Name</th>
                                    <th>Display Name</th>
                                    <th>Email</th>
                                    
                                </tr>
                            </thead>
                        <tbody>
                         <% while(RS.next()){
                             RS2 = daoViewStudentDetails.selectStudentDetailsByID(RS.getString("fromUserID"));
                            
                             while(RS2.next()){%>
                             <tr>
                                <td> <%=RS.getString("fromUserID")%></td> 
                               <td> <%=RS2.getString("userName")%></td>
                               <td> <%=RS2.getString("displayName")%></td> 
                               <td> <%=RS2.getString("email")%></td> 
                             
                             </tr>
                            <% }
                          %>
                         
                         <%
                            }%>
                        </tbody>
                          </table>
                            <%
                             daoViewOfficeHoursReservations.closeConnection();
                             daoViewStudentDetails.closeConnection();
                    }
                    if(flag == true)
                    {
                        break;
                    }
                }
                daoOfficeHoursTable.closeConnection();
                if(flag == false){%>
                    <h1 style="text-align: center">You can't view reservations about invalid input !!!</h1>
                       
               <% }
            } catch (Exception ex) {
                System.out.print(ex);
            }    
        %>
    </body>
</html>
