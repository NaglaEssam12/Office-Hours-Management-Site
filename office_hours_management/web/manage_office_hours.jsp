<%-- 
    Document   : manage_office_hours
    Created on : Jan 4, 2021, 1:51:26 AM
    Author     : ME
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="dao.DaoOfficeHoursTable"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <%
                   DaoOfficeHoursTable daoOfficeHoursTable = new DaoOfficeHoursTable();
                   ResultSet RS ; 
                   boolean flag = false;
                   if(request.getParameter("button3")!= null){
                       String officeHoursID = request.getParameter("officeHoursID");
                       RS = daoOfficeHoursTable.selectAllData();
                       while(RS.next())
                       {
                           if(officeHoursID.equals(RS.getString("officeHoursID")))
                           {
                               flag = true;
                               break;
                           }
                       }
                       if(flag){
                        daoOfficeHoursTable.deleteData(officeHoursID); %>
                        <h1> You deleted the record successfully </h1>
                      <% }else{%>
                            <h1> You Can't Delete the record </h1>
                      <%  }%>
                       
                   <% } 
                     if(request.getParameter("button4")!= null){
                        RS = daoOfficeHoursTable.selectAllData();
                        while(RS.next())
                        {%>
                        In The record number <%= RS.getString("officeHoursID")%>: The user ID <%= RS.getString("userID")%> has a slot from <%=RS.getString("fromTime")%> to <%=RS.getString("toTime")%> in date <%=RS.getString("date")%> 
                        <%=RS.getString("state")%> in <%= RS.getString("location")%> <br>
                       <% }
                       daoOfficeHoursTable.closeConnection();
                      }

                   %>
        
    </body>
</html>
