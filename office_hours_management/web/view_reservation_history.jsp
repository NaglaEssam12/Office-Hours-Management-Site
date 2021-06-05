<%-- 
    Document   : view_reservation_history
    Created on : Jan 3, 2021, 12:37:39 AM
    Author     : ME
--%>

<%@page import="dao.DaoFilteration"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DaoViewStaffMemberMessages"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="dao.DaoOfficeHoursTable"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  type="text/css" href="style.css" >
        <title>Reservation History Filtration</title>
    </head>
    <body>
       <%
                   if(request.getParameter("slot")!= null)
                   {%>
                    <div class="container">
                        <form id="signup" action="history_reservations.jsp" id="my_captcha_form" method="GET">
                            <div class="header">
                                <h3> Reservations by Slot </h3>
                            </div>
                      <div class="sep"></div>
                              <div class="inputs">
                                <input list="From Time-To Time" placeholder="From Time-To Time "name="slot1" autofocus required /> 
                                <%
                                    DaoViewStaffMemberMessages daoViewStaffMemberMessages = new DaoViewStaffMemberMessages();
                                    DaoOfficeHoursTable daoOfficeHoursTable = new DaoOfficeHoursTable();
                                    DaoFilteration daoFilteration = new DaoFilteration();
                                    String email = request.getSession().getAttribute("email").toString();
                                    String staffMemberID = daoViewStaffMemberMessages.getStaffMemberID(email);
                                    ResultSet RS1 = null;
                                    RS1 = daoOfficeHoursTable.getSpecificSlots(staffMemberID);%>
                                    <datalist id = "From Time-To Time">
                                    <%ArrayList Times = new ArrayList();
                                    Times = daoFilteration.filterTimes(RS1);
                       
                                    for(int i = 0; i < Times.size();i++){
                                    %>
                                         <option value=<%= Times.get(i)%> >
                                    <% }
                                   daoOfficeHoursTable.closeConnection();
                                   %>
                                    </datalist>
                                <input id="submit" name = "button1" type="submit" value="View History of Reservations"><br>
                             </div>
                        </form> 
                     
                    </div>
                  <% }
                    
                   if(request.getParameter("date")!= null){%>
                    <div class="container">
                        <form id="signup" action="history_reservations.jsp" id="my_captcha_form" method="GET">
                            `<div class="header">

                    `           <h3> Reservations by Date </h3>

                            </div>

                            <div class="sep"></div>
                            <div class="inputs">
                                <input type="text" placeholder="From Date" name="date1"  autofocus/><br>
                                <input type="text" placeholder="To Date" name="date2"  autofocus/><br>
                                <input id="submit" name = "button2" type="submit" value="View History of Reservations">
                            </div>
                        </form> 
                
                    </div>
                            <%}

                   
                   %>
    </body>
</html>
