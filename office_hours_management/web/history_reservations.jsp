<%-- 
    Document   : history_reservations
    Created on : Jan 3, 2021, 12:50:45 AM
    Author     : ME
--%>

<%@page import="dao.DaoViewStudentDetails"%>
<%@page import="java.time.LocalDate"%>
<%@page import="dao.DaoOfficeHoursTable"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DaoViewOfficeHoursReservations"%>
<%@page import="dao.DaoViewStaffMemberMessages"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  type="text/css" href="style.css" >
        <title>Reservations History</title>
    </head>
    <body>
        
         <%
                
                   DaoViewStaffMemberMessages daoViewStaffMemberMessages = new DaoViewStaffMemberMessages();
                   DaoViewOfficeHoursReservations daoViewOfficeHoursReservations = new DaoViewOfficeHoursReservations();
                   DaoOfficeHoursTable daoOfficeHoursTable = new DaoOfficeHoursTable();
                   DaoViewStudentDetails daoViewStudentDetails = new DaoViewStudentDetails();
                   String email = request.getSession().getAttribute("email").toString();
                   String staffMemberID = daoViewStaffMemberMessages.getStaffMemberID(email);
                   ResultSet RS ;  String date = "";
                   ResultSet RS1 = daoOfficeHoursTable.selectAllData();
                   boolean flag = false;
                   ArrayList officeHoursIDs = new ArrayList();
                   if(request.getParameter("button1")!= null){
                       System.out.println("ssssss");
                    String time = request.getParameter("slot1");
                    String[] arrOfTime = time.split("-"); 
                    String fromTime = arrOfTime[0];  String toTime = arrOfTime[1];
                    System.out.println("fromTime " + fromTime + "ToTime" + toTime);
                    
                    //officeHoursIDs = daoOfficeHoursTable.selectSlotOfficeHours(fromTime, toTime, staffMemberID);
                    try {
                            while(RS1.next())
                            {
                                if(RS1.getString("fromTime").equals(fromTime) && RS1.getString("toTime").equals(toTime))
                                {
                                    flag = true;
                                    officeHoursIDs = daoOfficeHoursTable.selectSlotOfficeHours(fromTime, toTime, staffMemberID);
                                    
                                }
                                 if(flag == true)
                                 {
                                    break;
                                 }
                            }
                            daoOfficeHoursTable.closeConnection();  //RS1
                            if(flag == false ){%>
                                     <h1 style="text-align:center">You can't view reservations about invalid input !!!</h1>

                           <% }
                        } catch (Exception ex) {
                            System.out.print(ex);
                        }    
                    
                   }
                   if(request.getParameter("button2")!= null)
                    {
                        LocalDate date1;
                        String fromDate = request.getParameter("date1");
                        String toDate = request.getParameter("date2");
                        System.out.println("from "+fromDate + " "+toDate);
                        try {
                            while(RS1.next())
                            {
                                date1 = LocalDate.parse(RS1.getString("date"));
                                
                                if(date1.isAfter(LocalDate.parse(fromDate)) && date1.isBefore(LocalDate.parse(toDate))) //!!!LocalDate.parse("2018-05-05");
                                {
                                    flag = true;
                                    officeHoursIDs = daoOfficeHoursTable.selectOfficeHoursIDsByDate(fromDate, toDate, staffMemberID);
                                   
                                }
                                 
                            }
                            daoOfficeHoursTable.closeConnection();  //RS1
                            if(flag == false ){%>
                                     <h1 style="text-align:center">You can't view reservations about invalid input !!!</h1>
                           <% }
                        } catch (Exception ex) {
                            System.out.print(ex);
                        }    
                        
                        
                        
                    }
                     
                     boolean flag1 = false;
                     int counter = 0;
                if(officeHoursIDs.size() >= 1){%>
                    <table border = "1px" style="text-align: center; font-size: 25px;position: fixed;top: 20%;left: 50%;transform: translate(-50%, -50%);">
                    <%for(int i = 0; i < officeHoursIDs.size();i++)
                    {
                       ResultSet RS2 = daoViewOfficeHoursReservations.selectAllOfficeHoursIDs(); 
                       ResultSet RS3 = null; 
                       String subjectName = "";
                        while(RS2.next()){
                           if(RS2.getString("officeHoursID").equals(officeHoursIDs.get(i))){
                             //System.out.print("******************* "+ RS2.getString("officeHoursID") + "   " + officeHoursIDs.get(i));
                                counter++;
                                if(counter == 1){%>
                                <h1 style="text-align:center"> You have Reservations from   </h1>
                                
                            <thead style="color:red">
                                <tr>
                                    <th>Student ID</th>
                                    <th>User Name</th>
                                    <th>Display Name</th>
                                    <th>Email</th>
                                    <th>Day</th>
                                </tr>
                            </thead>
                           
                                <%}%>
                               
                                <%
                                
                                flag1 = true;
                                date = daoOfficeHoursTable.getSpecificSlotDate((String)officeHoursIDs.get(i));
                                RS3 = daoViewStudentDetails.selectStudentDetailsByID(RS2.getString("fromUserID"));
                               
                                  while(RS3.next()){%>
                                    <tr>
                                      <td> <%=RS2.getString("fromUserID")%></td> 
                                      <td> <%=RS3.getString("userName")%></td>
                                      <td> <%=RS3.getString("displayName")%></td> 
                                      <td> <%=RS3.getString("email")%></td> 
                                      <td>  <%= date%></td> 
                                    </tr>
                               
                               
                            <% }
                                    
                                }
                            }
                    }%>
                     </table>
                    
                    <%if(flag1 == false)
                    {%>
                        <h1 style="text-align:center">You did't have reservations yet!!!</h1>
                    <%}
                }%>
    </body>
</html>
