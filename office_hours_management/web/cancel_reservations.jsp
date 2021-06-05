<%-- 
    Document   : cancel_reservations
    Created on : Jan 2, 2021, 9:25:20 PM
    Author     : ME
--%>

<%@page import="dao.DaoFilteration"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DaoOfficeHoursTable"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="dao.DaoViewOfficeHoursReservations"%>
<%@page import="dao.DaoViewStaffMemberMessages"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  type="text/css" href="style.css" >
        <title>Cancel Reservations</title>
    </head>
    <body>
        <%
            DaoViewStaffMemberMessages daoViewStaffMemberMessages = new DaoViewStaffMemberMessages();
                        DaoOfficeHoursTable daoOfficeHoursTable = new DaoOfficeHoursTable();
                        DaoFilteration daoFilteration = new DaoFilteration();
                        String email = request.getSession().getAttribute("email").toString();
                        String staffMemberID = daoViewStaffMemberMessages.getStaffMemberID(email);
        %>
        <%
           
        if (request.getParameter("slot") != null) {%>
            
            
        <div class="container">    
            <form id="signup" action="<%=request.getContextPath()%>/cancel_specific_slot_and_day_reservations.jsp" id="my_captcha_form" method="GET">
                <div class="header">

                    <h3> Cancel Reservations by Slot </h3>

                </div>

                <div class="sep"></div>
                <div class="inputs">
                    <input list="From Time-To Time" placeholder="From Time-To Time "name="time" autofocus required /> 
                    <%
                        
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
                    <input list="dates" placeholder="Date "name="date" autofocus required /> 
                    <%
                         daoOfficeHoursTable = new DaoOfficeHoursTable();
                        RS1 = null;
                        RS1 = daoOfficeHoursTable.getSpecificSlots(staffMemberID);%>
                        <datalist id = "dates">
                    <%ArrayList dates = new ArrayList();
                        
                        dates = daoFilteration.filterDates(RS1);
                       
                        for(int i = 0; i < dates.size();i++){
                            
                    %>
                        
                        <option value=<%= dates.get(i)%> >
                       <% }
                       daoOfficeHoursTable.closeConnection();
                       %>
                        </datalist> 
                    <input id="submit" name = "button1" type="submit" value="Cancel Reservations">
                </div>
            </form>
             </div>
                
        <% }
        if (request.getParameter("date") != null) {%>
            

        <div class="container">  
            <form id="signup" action="<%=request.getContextPath()%>/cancel_specific_slot_and_day_reservations.jsp" id="my_captcha_form" method="GET">
                <div class="header">

                    <h3> Cancel Reservations by Date </h3>

                </div>

                <div class="sep"></div>
                <div class="inputs">
                   <input list="dates" placeholder="Date "name="date" autofocus required /> 
                    <%
                        ResultSet RS1 = null;
                        RS1 = daoOfficeHoursTable.getSpecificSlots(staffMemberID);%>
                        <datalist id = "dates">
                    <%    
                        ArrayList dates = new ArrayList();
                        
                        dates = daoFilteration.filterDates(RS1);
                       
                        for(int i = 0; i < dates.size();i++){
                            
                    %>
                        
                        <option value=<%= dates.get(i)%> >
                       <% }
                       daoOfficeHoursTable.closeConnection();
                       %>
                        </datalist>    
                    <input id="submit" name = "button2" type="submit" value="Cancel Reservations">
                </div>
            </form>
        </div>
                
        <%}


        %>
    
    </body>
</html>
