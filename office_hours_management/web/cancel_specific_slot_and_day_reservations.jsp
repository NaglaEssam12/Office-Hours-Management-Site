<%-- 
    Document   : cancel_specific_slot_and_day_reservations
    Created on : Jan 2, 2021, 7:13:22 PM
    Author     : ME
--%>

<%@page import="controller.NotifyUser"%>
<%@page import="dao.DaoFinder"%>
<%@page import="dao.DaoCancelResrvation"%>
<%@page import="dao.DaoOfficeHoursTable"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="dao.DaoViewOfficeHoursReservations"%>
<%@page import="dao.DaoViewStaffMemberMessages"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  type="text/css" href="style.css" >
        <title>Cancel Specific Slot Or Day Reservations</title>
    </head>
    <body>
       
        <%
                   DaoViewStaffMemberMessages daoViewStaffMemberMessages = new DaoViewStaffMemberMessages();
                   DaoViewOfficeHoursReservations daoViewOfficeHoursReservations = new DaoViewOfficeHoursReservations();
                   DaoOfficeHoursTable daoOfficeHoursTable = new DaoOfficeHoursTable();
                   DaoCancelResrvation daoCancelResrvation = new DaoCancelResrvation();
                   DaoFinder daoFinder = new DaoFinder();
                   NotifyUser notifyUser = new NotifyUser();
                   boolean flag = false , flag1 = false; 
                   ResultSet RS1 = daoOfficeHoursTable.selectAllData();
                   ResultSet RS = daoViewOfficeHoursReservations.selectAllOfficeHoursIDs();
                   ResultSet RS3 = null;
                   String studentEmail = "";
                   String email = request.getSession().getAttribute("email").toString();
                   if(request.getParameter("button1")!= null){
                        
                        String staffMemberID = daoViewStaffMemberMessages.getStaffMemberID(email);
                        String time = request.getParameter("time");
                        String date = request.getParameter("date");
                        String[] arrOfTime = time.split("-"); 
                        String fromTime = arrOfTime[0];  String toTime = arrOfTime[1];
                       
                        System.out.println("fromTime " + fromTime + "ToTime" + toTime);
                        
                        try {
                            while(RS1.next())
                            {
                                System.out.println("DDDDDDDDDDDDDD");
                                if(RS1.getString("fromTime").equals(fromTime) && RS1.getString("toTime").equals(toTime)&& RS1.getString("date").equals(date))
                                {
                                    
                                    System.out.println("cccccccccccccc");
                                    flag1 = true;
                                    String officeHoursID = daoOfficeHoursTable.selectOfficeHours(fromTime, toTime, date, staffMemberID);
                                    while(RS.next())
                                    {
                                        if(RS.getString("officeHoursID").equals(officeHoursID))
                                        {
                                           System.out.println("RRRRRRRRRRRRRRRR");
                                           flag = true;
                                          
                                           RS3 = daoViewOfficeHoursReservations.getReservationFromID(officeHoursID);
                                           daoViewOfficeHoursReservations.selectReservarionSpecificSlotID(officeHoursID);
                                           String ID = "";
                                           while(RS3.next())
                                           {
                                                ID = RS3.getString("fromUserID");
                                                System.out.println("ID "+ID);
                                                studentEmail = daoFinder.findContactForUser(Integer.parseInt(ID.toString()));
                                                notifyUser.sendEmail("officeorganization15@gmail.com", studentEmail, "Cancel Reservation", "Cancel Reservation from "+email);
                                                notifyUser.notify(email, studentEmail, "Cancel Reservation from " + email);
                                           }
                                          
                                            
                                           
                                        }
                                        
                                        if(flag == true)
                                        {
                                            daoFinder.closeConnection();
        %>
                                             <h1 style="text-align:center">You have canceled all reservations successfully </h1>
                                            <%break;
                                        }
                                    }
                                    daoViewOfficeHoursReservations.closeConnection();  //RS
                                   
                                    
                                }
                                 if(flag1 == true)
                                 {
                                    break;
                                 }
                            }
                            daoOfficeHoursTable.closeConnection();  //RS1
                            if(flag == false || flag1 == false){%>
                                     <h1 style="text-align:center">You can't cancel reservations about invalid input !!!</h1>

                           <% }
                        } catch (Exception ex) {
                            System.out.print(ex);
                        }    
                   }
                   if(request.getParameter("button2")!= null)
                    {
                            String date = request.getParameter("date");
                            System.out.println("Date "+date);
                            try {

                            while(RS1.next())
                            {
                                if(RS1.getString("date").equals(date))
                                {
                                    flag1 = true;
                                  
                                     while(RS.next())
                                    {
                                            ResultSet RS2 = daoOfficeHoursTable.getSpecificOfficeHoursIDS(date);
                                        
                                            String ID = "";
                                            while (RS2.next()) {
                                                ID = RS2.getString("officeHoursID");
                                                if(RS.getString("officeHoursID").equals(ID))
                                                {
                                                     flag = true;
                                                    RS3 = daoViewOfficeHoursReservations.getReservationFromID(ID);
                                                    daoViewOfficeHoursReservations.selectReservarionSpecificSlotID(ID);
                                                    ID = "";
                                                    while(RS3.next())
                                                    {
                                                         ID = RS3.getString("fromUserID");
                                                         System.out.println("ID "+ID);
                                                         studentEmail = daoFinder.findContactForUser(Integer.parseInt(ID.toString()));
                                                         notifyUser.sendEmail("officeorganization15@gmail.com", studentEmail, "Cancel Reservation", "Cancel Reservation from "+email);
                                                         notifyUser.notify(email, studentEmail, "Cancel Reservation from " + email);
                                                    }
                                                }
                                            }
                                            if(flag == true)
                                            {%>
                                                     <h1 style="text-align:center">You have canceled all reservations successfully </h1>
                                                   <%
                                            }
                                    }
                                     daoFinder.closeConnection();
                                     daoViewOfficeHoursReservations.closeConnection();   //RS
                                        
                                    }
                                   
                                
                                if(flag1 == true)
                                {
                                    break;
                                }
                            }
                            daoOfficeHoursTable.closeConnection();   //RS1
                           
                            if(flag == false || flag1 == false){%>
                                     <h1 style="text-align:center">You can't cancel reservations about invalid input !!!</h1>

                           <% }
                        } catch (Exception ex) {
                            System.out.print(ex);
                        }    
                    }
                   
        %>
       
             
             
                
    </body>
</html>
